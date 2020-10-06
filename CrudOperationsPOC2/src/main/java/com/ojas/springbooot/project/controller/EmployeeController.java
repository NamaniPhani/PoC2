package com.ojas.springbooot.project.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.springbooot.project.model.Employee;
import com.ojas.springbooot.project.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	Logger log = Logger.getLogger(this.getClass());

	@PostMapping(value = "/save", produces = { "application/json", "application/xml" }, consumes = { "application/json",
			"application/xml" })
	public ResponseEntity<Object> save(@RequestBody Employee emp) {
		log.debug("Incoming request to the controller : " + emp);
		if (emp != null) {
			ResponseEntity<Object> saveOrUpdate = employeeService.save(emp);
			return new ResponseEntity<>(saveOrUpdate, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Invalid UserName", HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping(value = "/getAllEmployees", produces = { "application/json", "application/xml" })
	public ResponseEntity<Object> getAll(@PathVariable int pageNo, @PathVariable int pageSize) throws Exception {
		log.debug("Incoming request to the controller for getAll : ");
		ResponseEntity<Object> allUsers = employeeService.getAllEmployees(pageNo, pageSize);
		return new ResponseEntity<Object>(allUsers, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getById/{userId}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Object> getById(@PathVariable("empId") int empId) {
		log.debug("Incoming request to the controller for getById : ");
		ResponseEntity<Object> byId = employeeService.getByEmpId(empId);
		return new ResponseEntity<Object>(byId, HttpStatus.OK);
	}
	 @DeleteMapping("/deleteById/{userId}") 
	  public ResponseEntity<Object> delete(@PathVariable("empId") int empId) { 
	  employeeService.delete(empId); 
	  return new ResponseEntity<Object>("Deleted Successfully: " + empId, HttpStatus.OK);
	  
	  }
	 
	/*
	 * @PostMapping public ResponseEntity<Object> pagination(@PathVariable int
	 * pageNo, @PathVariable int pageSize) throws Exception{ ResponseEntity<Object>
	 * allEmployees = employeeService.getAllEmployees(pageNo, pageSize); return new
	 * ResponseEntity<Object>(allEmployees, HttpStatus.OK);
	 * 
	 * }
	 */

}
