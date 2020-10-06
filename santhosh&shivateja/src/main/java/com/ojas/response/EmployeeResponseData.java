package com.ojas.response;

import java.util.List;

import com.ojas.model.Employee;

public class EmployeeResponseData {

	private Long noOfrecords;
	private Integer totalNumberOfPages;
	private List<Employee> list;

	public Long getNoOfrecords() {
		return noOfrecords;
	}

	public void setNoOfrecords(Long noOfrecords) {
		this.noOfrecords = noOfrecords;
	}

	public Integer getTotalNumberOfPages() {
		return totalNumberOfPages;
	}

	public void setTotalNumberOfPages(Integer totalNumberOfPages) {
		this.totalNumberOfPages = totalNumberOfPages;
	}

	public List<Employee> getList() {
		return list;
	}

	public void setList(List<Employee> list) {
		this.list = list;
	}

}
