package com.homework.service;

import java.util.List;

import com.homework.dao.BookMarkDao;
import com.homework.domain.BookMark;
import com.homework.exception.MsgException;
import com.homework.factory.BasicFactory;

public class BookMarkServiceImpl implements BookMarkService {

	BookMarkDao dao = BasicFactory.getFactory().getInstance(BookMarkDao.class);

	public void add(BookMark bookmark) throws MsgException {

		if (dao.findbyTitle(bookmark.getTitle()) != null) {
			throw new MsgException("该书签标题已存在");
		}
		dao.addBookMark(bookmark);
	}

	public void delete(BookMark bookmark) {
		// TODO Auto-generated method stub
	}

	public BookMark queryById(Integer Id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<BookMark> queryByList() {
		// TODO Auto-generated method stub
		return null;
	}

}
