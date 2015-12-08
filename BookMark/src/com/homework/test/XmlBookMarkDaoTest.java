package com.homework.test;

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
}
