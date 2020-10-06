package com.ojas.service;

import org.springframework.http.ResponseEntity;

import com.ojas.model.Employee;

public interface EmployeeService {
	public ResponseEntity<Object> saveEmployee(Employee employeerequest) throws Exception;
	
	public ResponseEntity<Object> updateEmployee(Employee employeerequest) throws Exception;
	
	public ResponseEntity<Object> deleteEmployee(Integer id) throws Exception;
	
	public ResponseEntity<Object> getByIdEmployee(Integer id) throws Exception;
	
	public ResponseEntity<Object> getAllEmployees(int pageNo, int pageSize) throws Exception;

}
