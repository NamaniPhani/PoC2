package com.ojas.poc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.poc.exception.PaginationResponse;
import com.ojas.poc.exception.RecordNotFoundException;
import com.ojas.poc.model.Employee;
import com.ojas.poc.response.Response;
import com.ojas.poc.service.EmployeeService;

@RestController
@RequestMapping("/poc2/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/save")
	public Response saveEmployee(@Valid @RequestBody Employee employee) {
		Response saveEmployee = employeeService.saveEmployee(employee);
		return saveEmployee;
	}

	@GetMapping("/getbyid/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Integer id) throws RecordNotFoundException {
		Employee getById = employeeService.getById(id);
		if (getById != null)
			return new ResponseEntity<Employee>(getById, HttpStatus.OK);
		else
			return new ResponseEntity<Employee>(getById, HttpStatus.CONFLICT);
	}

	@PostMapping("/update")
	public ResponseEntity<Object> updateEmployee(@RequestBody Employee employee) {
		Employee updateEmployee = employeeService.updateEmployee(employee);
		if (updateEmployee != null)
			return new ResponseEntity<Object>(updateEmployee, HttpStatus.OK);
		else
			return new ResponseEntity<Object>("Employee is not updated!", HttpStatus.CONFLICT);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable("id") Integer id) throws RecordNotFoundException {
		employeeService.deleteEmployee(id);
		return new ResponseEntity<Object>(new Response("record deleted", 200), HttpStatus.OK);
	}

	@GetMapping("/getAll/{pageNo}/{pageSize}")
	public ResponseEntity<Object> getAll(PaginationResponse peginationres) {
		PaginationResponse all = employeeService.getAll(peginationres);
		if (all != null) {
			return new ResponseEntity<Object>(all, HttpStatus.OK);
		}
		return new ResponseEntity<Object>(new Response("Records are not available", 409), HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/findname")
	public ResponseEntity<Object> findByName(@RequestParam("name") String name) {
		List<Employee> findByName = employeeService.findByName(name);
		if (!findByName.isEmpty())
			return new ResponseEntity<Object>(findByName, HttpStatus.OK);
		else
			return new ResponseEntity<Object>(name + " is not exists in database", HttpStatus.BAD_REQUEST);
	}
}
