package com.demo.example.pagination;

public class StatesRequest {
	
	private int pageSize;
	private int pageNo;

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	@Override
	public String toString() {
		return "StatesRequest [pageSize=" + pageSize + ", pageNo=" + pageNo + "]";
	}
	
	

}
