package com.homework.domain;

import java.util.Date;

import com.homework.exception.MsgException;

public class BookMark {

	private Integer id;
	private String title;
	private String url;
	private Date created;

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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
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
