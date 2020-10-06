package com.example.response;

import java.util.List;

import com.example.model.Employee;

import lombok.Data;


@Data
public class SuccessResponse {

	private String statusCode;
	private String statusMessage;
	private List<Employee> employeeList;
	

	

	
}
