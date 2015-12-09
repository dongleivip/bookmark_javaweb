package com.homework.domain;

import java.util.Date;

import com.homework.exception.MsgException;

public class BookMark {

	private Integer id;
	private String title;
	private String url;
	private Date date;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void checkValue() throws MsgException {
		if (title == null || "".equals(title)) {
			throw new MsgException("书签名称不能为空");
		}
	}

	@Override
	public String toString() {
		return title + " : " + url;
	}
}
