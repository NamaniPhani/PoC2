package com.ojas.springbooot.project.util;

import java.util.List;

import com.ojas.springbooot.project.model.Employee;

public class Response {
	private String statusCode;
	private String status;
	private String msg;
    private List<Employee> data;
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<Employee> getData() {
		return data;
	}
	public void setData(List<Employee> findAll) {
		this.data = findAll;
	}
}
