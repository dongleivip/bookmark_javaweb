package com.homework.service;

import java.util.List;

import com.homework.domain.BookMark;
import com.homework.exception.MsgException;

public interface BookMarkService {

	/**
	 * 添加书签s
	 * 
	 * @param bookmark
	 * @throws MsgException
	 */
	public void add(BookMark bookmark) throws MsgException;

	/**
	 * 根据书签Id删除书签
	 * @param Id 书签Id
	 */
	public void deleteById(Integer id);

	/**
	 * 根据书签ID查找
	 * 
	 * @param Id
	 * @return 如果存在返回书签对象，如果不存在返回null
	 */
	public BookMark queryById(Integer id);

	/**
	 * 根据title来查询书签
	 * 
	 * @param title
	 *            书签名称
	 * @return 如果存在返回书签对象，如果不存在返回null
	 */
	public BookMark queryByTitle(String title);

	/**
	 * 查询书签列表
	 * 
	 * @return 返回所有书签的集合
	 */
	public List<BookMark> queryAllByList();
}
