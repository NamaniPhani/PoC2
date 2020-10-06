package com.demo.example.response;

import java.time.LocalDateTime;
import java.util.List;

import com.demo.example.entity.Employee;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Response {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
	private String statusCode;
	private String statusMessage;
	private List<Employee> listofEmployees;

	

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

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public List<Employee> getListofEmployees() {
		return listofEmployees;
	}

	public void setListofEmployees(List<Employee> listofEmployees) {
		this.listofEmployees = listofEmployees;
	}

}
