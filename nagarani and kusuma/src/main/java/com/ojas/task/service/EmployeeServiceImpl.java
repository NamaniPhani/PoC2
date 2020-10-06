package com.ojas.task.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ojas.task.model.Employee;
import com.ojas.task.model.Response;
import com.ojas.task.repository.EmployeeRepo;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	EmployeeRepo repository;

	public Response save(Employee employee) {

		logger.debug("Inside save method in service ");
		Employee emp = null;
		Response response = null;

		try {
			emp = repository.save(employee);
			response = new Response(emp, "Employee Saved Sucessfully", "200");
		} catch (Exception e) {
			logger.error("Inside catch block of save method in service");
			response = new Response(null, "Failed To Save The Employee ", "409");
		}
		return response;
	}

	@Override
	public Response findById(Integer id) {
		logger.debug("Inside findById method in  service");

		Optional<Employee> empList = null;
		Response response = null;

		try {
			empList = repository.findById(id);
			if (!empList.isPresent())
				response = new Response(null, "No Records Found", "400");
			else
				response = new Response(empList, "Record Found", "200");
		} catch (Exception e) {

			logger.error("Inside catch block of findById method in service");
			response = new Response(null, "Give Proper Data", "409");
		}
		return response;
	}

	@Override
	public Response findAll(Integer pageNo, Integer pageSize) {
		logger.debug("Inside findAll method in service");
		Sort sort = null;
		Pageable pageable = null;
		List<Employee> list = null;
		Response response = null;

		try {
			sort = Sort.by("name").ascending();
			pageable = PageRequest.of(pageNo, pageSize, sort);
			list = repository.findAll(pageable).toList();
			if (list.isEmpty())
				response = new Response(null, "No Records Found", "400");
			else
				response = new Response(list, "Records Found", "200");
		} catch (Exception e) {
			logger.error("Inside catch block of findAll method in service");
			response = new Response(null, "Provide Proper Data", "409");
		}
		return response;
	}

	@Override
	public Response findAll() {
		logger.debug("Inside findAll method in service");
		List<Employee> list = null;
		Response response = null;

		try {
			list = repository.findAll();
			if (list.isEmpty())
				response = new Response(null, "No Records Found", "400");
			else
				response = new Response(list, "Records Found", "200");
		} catch (Exception e) {
			logger.error("Inside catch block of findAll method in service");
			response = new Response(null, "Provide Proper Data", "409");
		}
		return response;
	}

	@Override
	public Response delete(Integer id) {
		logger.debug("Inside delete method in service");
		Response response = null;
		try {
			repository.deleteById(id);
			response = new Response(id, "Sucessfully Deleted Employee With Id " + id, "200");
		} catch (Exception e) {
			logger.error("Inside catch block of delete method in service");
			response = new Response(null, "Employee Not Found With Id " + id, "409");
		}
		return response;
	}

}
