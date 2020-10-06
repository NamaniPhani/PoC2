package com.ojas.poc.exception;

import java.util.List;

import com.ojas.poc.model.Employee;

public class PaginationResponse {

	private Integer pageSize;
	private Integer pageNo;
	private Long totalRecord;
	private List<Employee> emp;
	
	public List<Employee> getEmp() {
		return emp;
	}

	public void setEmp(List<Employee> emp) {
		this.emp = emp;
	}

	public PaginationResponse() {
		super();
	}

	public PaginationResponse(Integer pageSize, Integer pageNo, Long totalRecord) {
		super();
		this.pageSize = pageSize;
		this.pageNo = pageNo;
		this.totalRecord = totalRecord;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Long getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(Long totalRecord) {
		this.totalRecord = totalRecord;
	}

	@Override
	public String toString() {
		return "PaginationResponse [pageSize=" + pageSize + ", pageNo=" + pageNo + ", totalRecord=" + totalRecord + "]";
	}

}
