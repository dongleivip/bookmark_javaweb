package com.homework.test;

import java.util.Date;

import org.junit.Test;

import com.homework.dao.BookMarkDao;
import com.homework.domain.BookMark;
import com.homework.domain.Page;
import com.homework.domain.PageRequest;
import com.homework.factory.BasicFactory;

public class MySqlBookMarkDaoTest {

	@Test
	public void testFindBookMarkByTitle(){
		BookMarkDao dao = BasicFactory.getFactory().getInstance(BookMarkDao.class);
		BookMark bookmark = dao.querybyTitle("Google");
		System.out.println(bookmark.toString());
	}


	@Test
	public void testAddBookMark(){
		BookMarkDao dao = BasicFactory.getFactory().getInstance(BookMarkDao.class);
		BookMark bookmark = new BookMark();
		bookmark.setTitle("Google");
		bookmark.setUrl("www.google.com");
		bookmark.setCreated(new Date());
		dao.addBookMark(bookmark);
	}
	
	@Test
	public void testCountRows(){
		
		//BookMarkDao dao = BasicFactory.getFactory().getInstance(BookMarkDao.class);
		
		//String sql= "select count(*) from tb_bookmark where 1=1 and title like ?";
		//List<Object> list = new ArrayList<Object>();
		//list.add("%g%");
		//Object [] params = {"%g%"};
		//System.out.println(dao.getTotalRows(sql,params));
		//System.out.println(dao.getTotalRows(sql,list.toArray()));
	}
	
	@Test
	public void testPage(){
		
		BookMarkDao dao = BasicFactory.getFactory().getInstance(BookMarkDao.class);
		
		BookMark bookmark = new BookMark();
		bookmark.setTitle("g");
		
		PageRequest req = new PageRequest();
		
		Page<BookMark> page = dao.querybyPage(bookmark,req);
		
		System.out.println(page.getTotalRows());
	}
	
}
