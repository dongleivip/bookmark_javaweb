package com.homework.service;

import java.util.List;

import com.homework.dao.BookMarkDao;
import com.homework.domain.BookMark;
import com.homework.exception.MsgException;
import com.homework.factory.BasicFactory;

public class BookMarkServiceImpl implements BookMarkService {

	BookMarkDao dao = BasicFactory.getFactory().getInstance(BookMarkDao.class);

	public void add(BookMark bookmark) throws MsgException {

		if (dao.querybyTitle(bookmark.getTitle()) != null) {
			throw new MsgException("该书签标题已存在");
		}
		dao.addBookMark(bookmark);
	}

	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	public BookMark queryById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public BookMark queryByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<BookMark> queryAllByList() {
		// TODO Auto-generated method stub
		return null;
	}

}
