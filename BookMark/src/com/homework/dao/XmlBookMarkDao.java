package com.homework.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.homework.domain.BookMark;
import com.homework.domain.Page;
import com.homework.domain.PageRequest;
import com.homework.util.XmlDaoUtils;

@SuppressWarnings(value = { "unchecked", "deprecation" })
public class XmlBookMarkDao implements BookMarkDao {

	public BookMark querybyTitle(String title) {
		Document dom = XmlDaoUtils.getDom();
		Element root = dom.getRootElement();

		// 在xml中查找具有title属性等于传入title的bookmark元素
		List<Element> list = root.selectNodes("//bookmark[@title='" + title
				+ "']");

		if (list.size() > 0) {
			BookMark bookmark = new BookMark();
			Element ele = list.get(0);
			bookmark.setTitle(ele.attributeValue("title"));
			bookmark.setUrl(ele.attributeValue("url"));
			// bookmark.setDate(new Date(ele.attributeValue("created")));
			return bookmark;
		} else {
			return null;
		}
	}

	public void addBookMark(BookMark bookmark) {
		// 创建一个<book>元素
		Document dom = XmlDaoUtils.getDom();
		Element root = dom.getRootElement();
		Element bookmarkEle = DocumentHelper.createElement("bookmark");
		bookmarkEle.setAttributeValue("title", bookmark.getTitle());
		bookmarkEle.setAttributeValue("url", bookmark.getUrl());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		bookmarkEle
				.setAttributeValue("created", sdf.format(bookmark.getCreated()));

		// 挂载到<bookmarks>元素上
		root.add(bookmarkEle);

		// 写回到xml文件中
		XmlDaoUtils.refXml();

	}

	public BookMark findbyTitleAndUrl(String title, String url) {
		Document dom = XmlDaoUtils.getDom();
		Element root = dom.getRootElement();

		// 在xml中查找具有title属性等于传入title的bookmark元素
		List<Element> list = root.selectNodes("//bookmark[@title='" + title
				+ "' and @url='" + url + "']");

		if (list.size() > 0) {
			BookMark bookmark = new BookMark();
			Element ele = list.get(0);
			bookmark.setTitle(ele.attributeValue("title"));
			bookmark.setUrl(ele.attributeValue("url"));
			bookmark.setCreated(new Date(ele.attributeValue("created")));
			return bookmark;
		} else {
			return null;
		}
	}

	public List<BookMark> queryAllByList() {
		// 先不实现
		throw new NotImplementedException();
	}

	public BookMark queryById(Integer Id) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	public void deleteById(Integer Id) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}

	public List<BookMark> querybyKeyword(String keyword) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}
	
	public int getTotalRows() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Page<BookMark> querybyPage(BookMark bookmark, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		throw new NotImplementedException();
	}
	
}
