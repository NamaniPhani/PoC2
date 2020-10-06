package com.ojas.springbooot.project.service;

import org.springframework.http.ResponseEntity;

import com.ojas.springbooot.project.model.Employee;

public interface EmployeeService {
	public ResponseEntity<Object> save(Employee emp);
	//public ResponseEntity<Object> getAllEmployees();
	public ResponseEntity<Object> delete(int id);
	public ResponseEntity<Object> getByEmpId(int id);
	public ResponseEntity<Object> getAllEmployees(int pageNo, int pageSize) throws Exception;

}
