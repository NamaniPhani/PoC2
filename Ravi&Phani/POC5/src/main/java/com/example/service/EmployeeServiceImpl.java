package com.example.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.controller.EmployeeController;
import com.example.exception.CustomException;
import com.example.exception.Response;
import com.example.model.Employee;
import com.example.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmpServiceInf {
	@Autowired
	private EmployeeRepository employeeRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	Response response = new Response();

	public ResponseEntity<Object> saveOrUpdate(Employee employee) {
		Employee saveOrUpdate = employeeRepository.save(employee);

		LOGGER.info("executing EmployeeServiceImpl.saveOrUpdate()");
		if (saveOrUpdate != null) {
			return new ResponseEntity<>(saveOrUpdate, HttpStatus.OK);
		} else {
			try {
				throw new CustomException("Not valid");
			} catch (CustomException e) {
				e.printStackTrace();
			}
			return new ResponseEntity<>(saveOrUpdate, HttpStatus.BAD_REQUEST);
		}
	}

	public  ResponseEntity<Object> getAllEmployees(int pageNo, int pageSize) {

		LOGGER.info("executing EmployeeServiceImpl.getAllEmployees()");
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Employee> pagedResult = employeeRepository.findAll(pageable);

		if (pagedResult != null) {
			return new ResponseEntity<>(pagedResult, HttpStatus.OK);
			
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			
		}
		//return pagedResult.toList();
		

	}

	/*
	 * @Override public Optional<Employee> getById(int id) { return
	 * employeeRepository.findById(id); }
	 */

	@Override
	public Response getById(Integer id) {
		LOGGER.info("executing EmployeeServiceImpl.getById()");
		if (id == null) {
			try {
				throw new CustomException("please provide a valid id");
			} catch (CustomException e) {
				e.printStackTrace();
			}
		} else {
			Optional<Employee> findById = employeeRepository.findById(id);
			if (!findById.isPresent()) {
				try {
					throw new CustomException("no records found");
				} catch (CustomException e) {
					e.printStackTrace();
				}
			} else
				response.setObject(findById);
				response.setMessage("data found successfully");
				response.setStatusCode("200");
				return response;
		}
		return response;
	}
	
	
	@Override
	public void deleteById(int id) {
		employeeRepository.deleteById(id);
	}



}
