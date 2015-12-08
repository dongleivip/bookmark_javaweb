package com.homework.dao;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.homework.domain.BookMark;
import com.homework.util.XmlDaoUtils;

public class XmlBookMarkDao {

	public BookMark findbyTitle(String title) {
		Document dom = XmlDaoUtils.getDom();
		Element root = dom.getRootElement();

		// 在xml中查找具有title属性等于传入title的bookmark元素
		List<Element> list = root.selectNodes("//bookmark[@title='" + title + "']");

		if (list.size() > 0) {

			BookMark bookmark = new BookMark();
			Element ele = list.get(0);
			bookmark.setTitle(ele.attributeValue("title"));
			bookmark.setUrl(ele.attributeValue("url"));
			bookmark.setDate(ele.attributeValue("created"));
			return bookmark;
		} else {
			return null;
		}
	}

	public void addBookMark(BookMark bookmark) {
		//创建一个<book>元素
		Document dom = XmlDaoUtils.getDom();
		Element root = dom.getRootElement();
		Element bookmarkEle = DocumentHelper.createElement("bookmark");
		bookmarkEle.setAttributeValue("title",bookmark.getTitle());
		bookmarkEle.setAttributeValue("url",bookmark.getUrl());
		bookmarkEle.setAttributeValue("created",bookmark.getDate());
		
		//挂载到<bookmarks>元素上
		root.add(bookmarkEle);
		
		//写回到xml文件中
		XmlDaoUtils.refXml();
		
	}

	public BookMark findbyTitleAndUrl(String title, String url) {
		Document dom = XmlDaoUtils.getDom();
		Element root = dom.getRootElement();

		// 在xml中查找具有title属性等于传入title的bookmark元素
		List<Element> list = root.selectNodes("//bookmark[@title='"+title+"' and @url='"+url+"']");

		if (list.size() > 0) {
			BookMark bookmark = new BookMark();
			Element ele = list.get(0);
			bookmark.setTitle(ele.attributeValue("title"));
			bookmark.setUrl(ele.attributeValue("url"));
			bookmark.setDate(ele.attributeValue("created"));
			return bookmark;
		} else {
			return null;
		}
	}
}
