package com.poc5.response;

import java.util.List;

import com.poc5.model.Employee;

public class EmployeeResponse {

	private String statusCode;
	private String statusMessage;
	private List<Employee> emplist;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public List<Employee> getEmplist() {
		return emplist;
	}

	public void setEmplist(List<Employee> emplist) {
		this.emplist = emplist;
	}
}
