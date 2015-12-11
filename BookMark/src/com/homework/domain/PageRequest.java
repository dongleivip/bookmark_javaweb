package com.homework.domain;

public class PageRequest {

	protected int pageNo = 1;
	protected int pageSize = 10;//默认10
	
	public PageRequest(){
		
	}
	
	public PageRequest(int pageNo , int pageSize){
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}
	
	/**
	 * 获取当前页码
	 * @return
	 */
	public int getPageNo() {
		return pageNo;
	}
	
	/**
	 * 设置当前页码
	 * @param pageNo
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * 获得每页的记录数量,默认值:10.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页的记录数量,默认值:10.
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	/**
	 * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置, 序号从0开始.
	 */
	public int getOffset() {
		return ((pageNo - 1) * pageSize);
	}
	
}
