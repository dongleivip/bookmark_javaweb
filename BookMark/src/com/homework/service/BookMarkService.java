package com.homework.service;

import java.util.List;

import com.homework.domain.BookMark;
import com.homework.exception.MsgException;

public interface BookMarkService {

	/**
	 * 添加书签s
	 * @param bookmark
	 * @throws MsgException
	 */
	public void add(BookMark bookmark) throws MsgException;
	
	/**
	 * 删除书签
	 * @param bookmark
	 */
	public void delete(BookMark bookmark);
	
	/**
	 * 根据书签ID查找
	 * @param Id
	 * @return 如果存在返回书签对象，如果不存在返回null
	 */
	public BookMark queryById(Integer Id);
	
	/**
	 * 查询书签列表
	 * @return 返回所有书签的集合
	 */
	public List<BookMark> queryByList();
}
