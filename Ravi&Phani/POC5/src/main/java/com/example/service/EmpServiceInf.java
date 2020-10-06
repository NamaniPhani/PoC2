package com.example.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.exception.CustomException;
import com.example.exception.Response;
import com.example.model.Employee;

public interface EmpServiceInf {
	
	public ResponseEntity<Object> saveOrUpdate(Employee employee);
	/* public Employee saveOrUpdate(Employee employee);*/

	
	public ResponseEntity<Object> getAllEmployees(int pageNo, int pageSize);
	/*public List<Employee> getAllEmployees();*/

	
	public Response getById(Integer id);
	/* public Optional<Employee> getById(int id); */
	
	public void deleteById(int id);

	
	 
}
