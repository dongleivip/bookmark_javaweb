package com.homework.dao;

import java.util.List;

import com.homework.domain.BookMark;

public interface BookMarkDao {

	/**
	 * 添加新的书签
	 * 
	 * @param bookmark
	 */
	public void addBookMark(BookMark bookmark);

	/**
	 * 根据标题查询书签
	 * 
	 * @param title 标题
	 *            
	 * @return 书签对象，没有找到则返回null
	 */
	public BookMark findbyTitle(String title);

	/**
	 * 查询所有书签信息组成的集合
	 * 
	 * @return 封装了所有书签信息的集合,如果没有任何书签,返回的集合中内容为空
	 */
	List<BookMark> getAllBookMark();

}
