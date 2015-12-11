package com.homework.dao;

import java.util.List;

import com.homework.domain.BookMark;
import com.homework.domain.Page;
import com.homework.domain.PageRequest;

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
	 * @param title
	 *            标题
	 * 
	 * @return 书签对象，没有找到则返回null
	 */
	public BookMark querybyTitle(String title);

	/**
	 * 根据书签ID查找
	 * 
	 * @param Id
	 * @return 如果存在返回书签对象，如果不存在返回null
	 */
	public BookMark queryById(Integer id);

	/**
	 * 根据书签Id删除书签
	 * 
	 * @param Id
	 *            书签Id
	 */
	public void deleteById(Integer id);

	/**
	 * 查询所有书签信息组成的集合
	 * 
	 * @return 封装了所有书签信息的集合,如果没有任何书签,返回的集合中内容为空
	 */
	public List<BookMark> queryAllByList();

	/**
	 * 查询与关键字(title)相符的书签集合
	 * @param keyword
	 */
	public List<BookMark> querybyKeyword(String keyword);
	
	/**
	 * 查询数据库中一共有多少条记录
	 * @return
	 */
	public int getTotalRows();

	/**
	 * 根据条件进行分页查询
	 * @param bookmark 封装查询条件
	 * @param pageRequest 分页请求
	 * @return 返回带有分页后的数据集合
	 */
	public Page<BookMark> querybyPage(BookMark bookmark, PageRequest pageRequest);
	
}
