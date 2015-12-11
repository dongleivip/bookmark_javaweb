package com.homework.service;

import java.util.List;

import com.homework.dao.BookMarkDao;
import com.homework.domain.BookMark;
import com.homework.domain.Page;
import com.homework.domain.PageRequest;
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
		dao.deleteById(id);
	}

	public BookMark queryById(Integer id) {
		return dao.queryById(id);
	}

	public BookMark queryByTitle(String title) {
		return dao.querybyTitle(title);
	}

	public List<BookMark> queryAllByList() {
		return dao.queryAllByList();
	}

	public List<BookMark> queryByKeyword(String keyword) {
		return dao.querybyKeyword(keyword);
	}

	public Page<BookMark> queryByPage(BookMark bookmark, PageRequest pageRequest) {
		return dao.querybyPage(bookmark, pageRequest);
	}
}
