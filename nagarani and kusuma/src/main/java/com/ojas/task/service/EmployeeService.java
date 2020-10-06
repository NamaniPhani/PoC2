package com.ojas.task.service;

import com.ojas.task.model.Employee;
import com.ojas.task.model.Response;

public interface EmployeeService {

	public Response save(Employee employee);
	
	public Response findById(Integer id);
	
	public Response findAll(Integer pageNo,Integer pageSize);
	
	public Response delete(Integer id);
	
	public Response findAll();
}
