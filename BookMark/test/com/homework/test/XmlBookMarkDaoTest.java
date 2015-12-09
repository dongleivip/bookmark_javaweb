package com.homework.test;

import java.util.Date;

import org.junit.Test;

import com.homework.dao.XmlBookMarkDao;
import com.homework.domain.BookMark;

public class XmlBookMarkDaoTest {

	@Test
	public void testFindBookMarkByTitle(){
		XmlBookMarkDao dao = new XmlBookMarkDao();
		BookMark bookmark = dao.findbyTitle("标题");
		System.out.println(bookmark.toString());
	}

	@Test
	public void testFindBookMarkByTitleAndUrl(){
		XmlBookMarkDao dao = new XmlBookMarkDao();
		BookMark bookmark = dao.findbyTitleAndUrl("标题", "地址");
		System.out.println(bookmark);
	}

	@Test
	public void testAddBookMark(){
		XmlBookMarkDao dao = new XmlBookMarkDao();
		
		BookMark bookmark = new BookMark();
		bookmark.setTitle("Google");
		bookmark.setUrl("www.google.com");
		bookmark.setDate(new Date());
		dao.addBookMark(bookmark);
	}
}
