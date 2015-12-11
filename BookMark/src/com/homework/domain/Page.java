package com.homework.domain;

import java.util.List;

public class Page<T> extends PageRequest{
	
	// 共计多少条数据
	private int totalRows = -1;
	// 共计多少页
	private int totalPages = -1;
	private int firstpage = 1;
	private List<T> result = null;
	
	public Page() {
	}

	public Page(PageRequest request) {
		this.pageNo = request.pageNo;
		this.pageSize = request.pageSize;
	}
	
	public Page(int pageSize) {
		this.pageSize = pageSize;
	}
	

	/**
	 * 获得总记录数, 默认值为-1.
	 */
	public int getTotalRows() {
		return totalRows;
	}
	
	/**
	 * 设置总记录数.
	 */
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
		//并记录总页数
		this.setTotalPages(totalRows/pageSize + (totalRows % pageSize == 0?0:1));
	}

	/**
	 * 根据pageSize与totalItems计算总页数.
	 */
	public int getTotalPages() {
		return totalRows / pageSize + (totalRows % pageSize == 0 ? 0 : 1);
		//return (int) Math.ceil((double) totalItems / (double) getPageSize());
	}

	public int getFirstpage() {
		return firstpage;
	} 

	public void setFirstpage(int firstpage) {
		this.firstpage = firstpage;
	}

	public int getLastPage() {
		return totalRows / pageSize + (totalRows % pageSize == 0?0:1);
	}

	public int getPrePage() {
		return pageNo == 1 ? 1 : pageNo - 1;
	}

	public int getNextPage() {
		return pageNo == totalPages ? totalPages : pageNo + 1;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
}
