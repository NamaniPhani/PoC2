package com.poc5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc5.model.Employee;
import com.poc5.response.EmployeeResponse;
import com.poc5.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	EmployeeResponse empResponse = new EmployeeResponse();

	@PostMapping("/createEmployee")
	public ResponseEntity<Object> createEmployee(@RequestBody Employee empObj) {

		if (empObj != null && !empObj.getEmpName().isEmpty() && empObj.getPhoneNumber() != 0
				&& !empObj.getCity().isEmpty() && empObj.getSalary() >= 0) {
			empResponse = employeeService.createEmployee(empObj);

		}
		return new ResponseEntity<Object>(empResponse, HttpStatus.OK);
	}

	@GetMapping("/getEmployeeById/{id}")
	public ResponseEntity<Object> getEmployeeById(@PathVariable int id) {

		if (id >= 0) {
			EmployeeResponse readEmployee = employeeService.getEmployeeById(id);
			return new ResponseEntity<Object>(readEmployee, HttpStatus.OK);
		} else {
			empResponse.setStatusCode("422");
			empResponse.setStatusMessage("Invalid Employee request");
			return new ResponseEntity<Object>(empResponse, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@PostMapping("/updateEmployee")
	public ResponseEntity<Object> updateEmployee(@RequestBody Employee emp) {

		if (emp != null && !emp.getEmpName().isEmpty() && emp.getPhoneNumber() != 0 && !emp.getCity().isEmpty()
				&& emp.getSalary() >= 0) {
			EmployeeResponse updateEmployee = employeeService.updateEmployee(emp);
			return new ResponseEntity<Object>(updateEmployee, HttpStatus.OK);
		} else {
			empResponse.setStatusCode("422");
			empResponse.setStatusMessage("Invalid Employee request");
			return new ResponseEntity<Object>(empResponse, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@PostMapping("/deleteEmployee/{id}")
	public ResponseEntity<Object> deleteEmployee(@PathVariable int id) {
		if (id >= 0) {
			EmployeeResponse deleteEmployee = employeeService.deleteEmployee(id);
			return new ResponseEntity<Object>(deleteEmployee, HttpStatus.OK);
		} else {
			empResponse.setStatusCode("422");
			empResponse.setStatusMessage("Invalid Employee request");
			return new ResponseEntity<Object>(empResponse, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@GetMapping("/getAllEmployees/{pageNo}/{pageSize}")
	public ResponseEntity<Object> getAllEmployees(@PathVariable int pageNo, @PathVariable int pageSize) {
		Object allEmployees = employeeService.getAllEmployees(pageNo, pageSize);
		return new ResponseEntity<Object>(allEmployees, HttpStatus.OK);
	}
}
