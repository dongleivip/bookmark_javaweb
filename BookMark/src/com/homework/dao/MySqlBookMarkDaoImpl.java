package com.homework.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.homework.domain.BookMark;
import com.homework.util.DaoUtils;

public class MySqlBookMarkDaoImpl implements BookMarkDao {

	public void addBookMark(BookMark bookmark) {
		String sql = "insert into tb_bookmark values(null,?,?,?)";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, bookmark.getTitle(), bookmark.getUrl(),
					bookmark.getDate());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public BookMark querybyTitle(String title) {
		String sql = "select * from tb_bookmark where title = ?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<BookMark>(BookMark.class),
					title);
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
		try{
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql,id);
		}catch (Exception e) {
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

}
