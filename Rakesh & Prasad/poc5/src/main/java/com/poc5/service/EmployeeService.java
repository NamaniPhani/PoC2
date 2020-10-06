package com.poc5.service;

import com.poc5.model.Employee;
import com.poc5.response.EmployeeResponse;

public interface EmployeeService {

	public EmployeeResponse createEmployee(Employee e);

	public EmployeeResponse getEmployeeById(int id);

	public EmployeeResponse updateEmployee(Employee e);

	public EmployeeResponse deleteEmployee(int id);
	
	public Object getAllEmployees( int pageNo,  int pageSize);
	
	
}
