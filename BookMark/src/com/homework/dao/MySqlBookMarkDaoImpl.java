package com.homework.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang.StringUtils;

import com.homework.domain.BookMark;
import com.homework.domain.Page;
import com.homework.domain.PageRequest;
import com.homework.util.DaoUtils;

public class MySqlBookMarkDaoImpl implements BookMarkDao {

	public void addBookMark(BookMark bookmark) {
		String sql = "insert into tb_bookmark values(null,?,?,?)";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, bookmark.getTitle(), bookmark.getUrl(),bookmark.getCreated());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public BookMark querybyTitle(String title) {
		String sql = "select * from tb_bookmark where title = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<BookMark>(BookMark.class),title);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public BookMark queryById(Integer Id) {
		String sql = "select * from tb_bookmark where id = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<BookMark>(BookMark.class),Id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public void deleteById(Integer id) {
		String sql = "delete from tb_bookmark where id = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public List<BookMark> queryAllByList() {
		String sql = "select * from tb_bookmark";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<BookMark>(BookMark.class));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public List<BookMark> querybyKeyword(String keyword) {
		try {
			String sql = "select * from tb_bookmark where title like ?";
			// 第一个值是string,第二个值是number
			// Object[] params = new Object[]{"string", 5000};
			Object[] params = { "%" + keyword + "%" };// 参数数组
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanListHandler<BookMark>(BookMark.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public int getTotalRows() {
		String sql = "select count(*) from tb_bookmark";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long) runner.query(sql, new ScalarHandler())).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public int getTotalRows(String sql, Object... values) {
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return ((Long) runner.query(sql, new ScalarHandler(), values)).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public boolean isExits (BookMark bookmark) {
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("select * from tb_bookmark where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if (!StringUtils.isEmpty(bookmark.getTitle())) {
			buffer.append(" and title = ? ");
			params.add(bookmark.getTitle());
		}
		if (!StringUtils.isEmpty(bookmark.getUrl())) {
			buffer.append(" and url = ? ");
			params.add(bookmark.getUrl());
		}
		if (!StringUtils.isEmpty(bookmark.getCreated())) {
			buffer.append(" and created = ? ");
			params.add(bookmark.getCreated());
		}
		
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return  runner.query(buffer.toString(), new BeanHandler<BookMark>(BookMark.class), params.toArray()) != null;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public Page<BookMark> querybyPage(BookMark bookmark, PageRequest pageRequest) {
		try {
			StringBuffer buffer = new StringBuffer();
			buffer.append("select * from tb_bookmark where 1=1 ");
			List<Object> params = new ArrayList<Object>();
			if (!StringUtils.isEmpty(bookmark.getTitle())) {
				buffer.append(" and title like ? ");
				params.add("%" + bookmark.getTitle() + "%");
			}
			if (!StringUtils.isEmpty(bookmark.getUrl())) {
				buffer.append(" and url like ? ");
				params.add("%" + bookmark.getUrl() + "%");
			}

			// 先查询总数量
			String countSql = prepareCountSql(buffer.toString());
			int totalRows = getTotalRows(countSql, params.toArray());

			// 再进行分页查询
			buffer.append(" limit ?,?");
			int from = (pageRequest.getPageNo() - 1) * pageRequest.getPageSize();
			int count = pageRequest.getPageSize();
			params.add(from);
			params.add(count);

			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			String sql = buffer.toString();
			List<BookMark> list = runner.query(sql,new BeanListHandler<BookMark>(BookMark.class),params.toArray());
			
			Page<BookMark> page = new Page<BookMark>(pageRequest);
			page.setResult(list);
			page.setTotalRows(totalRows);

			return page;

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public int saveByList(List<BookMark> list) {
		int count = 0;
		if(list != null){
			for(BookMark model : list){
				if(!isExits(model)){
					count += add(model);
				}
			}
		}
		return count;
	}
	
	public int add(BookMark bookmark){
		String sql = "insert into tb_bookmark values(null,?,?,?)";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());

			// json里的数据是没有url的...
			String url = "";
			if(!StringUtils.isEmpty(bookmark.getUrl())){
				url = bookmark.getUrl();
			}
			
			Object[] params = {bookmark.getTitle(),url,bookmark.getCreated()};
			return runner.update(sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	/* 下面是辅助函数 */

	private static String removeOrders(String sql) {
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*",Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(sql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}

	private static String removeSelect(String sql) {
		int beginPos = sql.toLowerCase().indexOf("from");
		return sql.substring(beginPos);
	}

	private static String removeLimit(String sql) {
		if (sql.contains("limit")) {
			int endPos = sql.toLowerCase().indexOf("limit");
			sql = sql.substring(0, endPos);
		}
		return sql;
	}

	private String prepareCountSql(String orgSql) {
		String countSql = "select count(*) " + removeLimit(removeSelect(removeOrders(orgSql)));
		return countSql;
	}

	
}
