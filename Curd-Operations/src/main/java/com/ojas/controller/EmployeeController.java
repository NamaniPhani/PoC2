package com.ojas.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.model.Employee;
import com.ojas.service.impl.EmployeeServiceImpl;

@RestController
@RequestMapping("employee")
public class EmployeeController {
 
	@Autowired
	private EmployeeServiceImpl empservice;

	static Logger log = Logger.getLogger(EmployeeController.class.getName());

	@PostMapping("/save")
	public ResponseEntity<Object> saveEmployee(@RequestBody Employee employeerequest) throws Exception {
		log.debug("Incoming request employee controller save method : " + employeerequest);
		ResponseEntity<Object> saveEmployee = empservice.saveEmployee(employeerequest);
		return new ResponseEntity<>(saveEmployee, HttpStatus.OK);
	}
 
	@PutMapping("/update")
	public ResponseEntity<Object> updateEmployee(@RequestBody Employee employeerequest) throws Exception {
		log.debug("Incoming request employee controller update method : " + employeerequest);
		ResponseEntity<Object> updateEmployee = empservice.updateEmployee(employeerequest);
		return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
	}

	@DeleteMapping("/deletebyid/{id}")
	public ResponseEntity<Object> deleteByIdEmployee(@PathVariable Integer id) throws Exception {
		log.debug("Incoming request employee controller delete method : " + id);
		ResponseEntity<Object> deleteEmployee = empservice.deleteEmployee(id);
		return new ResponseEntity<>(deleteEmployee, HttpStatus.OK);
	}

	@GetMapping("/getbyid/{id}")
	public ResponseEntity<Object> getByIdEmployee(@PathVariable Integer id) throws Exception {
		log.debug("Incoming request employee controller getbyid method : " + id);
		ResponseEntity<Object> byIdEmployee = empservice.getByIdEmployee(id);
		return new ResponseEntity<>(byIdEmployee, HttpStatus.OK);
	}

	@GetMapping("/getall/{pageNo}/{pageSize}")
	public ResponseEntity<Object> getAllEmployees(@PathVariable int pageNo, @PathVariable int pageSize)
			throws Exception { 
		log.debug("Incoming request employee controller getall method ");
		ResponseEntity<Object> allEmployees = empservice.getAllEmployees(pageNo, pageSize);
		return new ResponseEntity<>(allEmployees, HttpStatus.OK);
	}
}
