package com.homework.test;

import java.util.Date;

import org.junit.Test;

import com.homework.dao.BookMarkDao;
import com.homework.domain.BookMark;
import com.homework.factory.BasicFactory;

public class MySqlBookMarkDaoTest {

	@Test
	public void testFindBookMarkByTitle(){
		BookMarkDao dao = BasicFactory.getFactory().getInstance(BookMarkDao.class);
		BookMark bookmark = dao.findbyTitle("Google");
		System.out.println(bookmark.toString());
	}


	@Test
	public void testAddBookMark(){
		BookMarkDao dao = BasicFactory.getFactory().getInstance(BookMarkDao.class);
		BookMark bookmark = new BookMark();
		bookmark.setTitle("Google");
		bookmark.setUrl("www.google.com");
		bookmark.setDate(new Date());
		dao.addBookMark(bookmark);
	}
}
