package com.homework.service;

import java.util.List;

import com.homework.domain.BookMark;
import com.homework.domain.Page;
import com.homework.domain.PageRequest;
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

	/**
	 * 查询与关键字(title)相符的书签
	 * @param keyword
	 * @return
	 */
	public List<BookMark> queryByKeyword(String keyword);

	/**
	 * 根据条件进行分页查询
	 * @param bookmark 封装查询条件
	 * @param pageRequest 分页请求
	 * @return 返回带有分页后的数据集合
	 */
	public Page<BookMark> queryByPage(BookMark bookmark, PageRequest pageRequest);
}
