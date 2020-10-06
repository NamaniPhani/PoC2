package com.demo.example.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.demo.example.entity.Employee;
import com.demo.example.response.Response;
import com.demo.example.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	Response response = new Response();

	@Autowired 
	private EmployeeService employeeService;

	@PostMapping("/createEmployee")
	public ResponseEntity<Object> postEmployee(@RequestBody Employee employee) {
		logger.debug("incoming request to create EmployeeController ::::" + employee);
		if (employee !=null && employee.getEmployeeId() > 0 && employee.getEmployeeId() != null && employee.getEmployeeName() != null
				&& !employee.getEmployeeName().isEmpty() && employee.getEmployeeSalary() >= 0
				&& employee.getEmployeeSalary() != null && !employee.getEmployeeCity().isEmpty()
				&& employee.getEmployeeCity() != null && employee.getEmployeePhoneNumber() != null) {

			ResponseEntity<Object> createEmployee = employeeService.createEmployee(employee);
			logger.debug("response for create  EmployeeController ::::" + createEmployee);
			return new ResponseEntity<Object>(createEmployee, HttpStatus.OK);

		} else {
			logger.debug("incoming request to createJob EmployeeController ::::" + "Invalid Job request");
			response.setTimestamp(LocalDateTime.now());
			response.setStatusCode("422");
			response.setStatusMessage("Invalid Employee request");
			return new ResponseEntity<Object>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

	@GetMapping("/findEmployeeById/{id}")
	public ResponseEntity<Object> findEmployeeById(@PathVariable int id) {
		logger.debug("incoming request for findEmployeeById EmployeeController ::::" + id);
		if (id >= 0) {
			ResponseEntity<Object> findEmployeeById = employeeService.findEmployeeById(id);
			logger.debug("response for findEmployeeById EmployeeController ::::" + findEmployeeById);
			return new ResponseEntity<Object>(findEmployeeById, HttpStatus.OK);
		} else {
			response.setTimestamp(LocalDateTime.now());
			response.setStatusCode("422");
			response.setStatusMessage("Invalid Employee request");
			return new ResponseEntity<Object>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@GetMapping("/findAllEmployees")
	public ResponseEntity<Object> findAllEmployees() {
		ResponseEntity<Object> findAllEmployees = employeeService.findAllEmployees();
		logger.debug("response for findAllEmployees EmployeeController ::::" + findAllEmployees);
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@PutMapping("/updateEmployee/{employeeId}")
	public ResponseEntity<Object> UpdateEmployee(@Valid @RequestBody Employee employee, @PathVariable int employeeId) {
		if (employeeId > 0 && employee != null) {
			ResponseEntity<Object> getAllJobs = employeeService.UpdateEmployee(employee, employeeId);
			logger.debug("response for findAllEmployees JobController ::::" + getAllJobs);
			return new ResponseEntity<Object>(getAllJobs, HttpStatus.OK);
		} else {
			logger.debug("incoming request for findAllEmployees EmployeeController ::::" + "Invalid Employee request");
			response.setTimestamp(LocalDateTime.now());
			response.setStatusCode("422");
			response.setStatusMessage("Invalid Employee request");
			return new ResponseEntity<Object>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@DeleteMapping("/deleteEmployeeById/{id}")
	public ResponseEntity<Object> deleteEmployeeById(@PathVariable int id) {
		if (id > 0) {
			ResponseEntity<Object> getAllJobs = employeeService.deleteEmployeeById(id);
			logger.debug("response for findAllEmployees EmployeeController ::::" + getAllJobs);
			return new ResponseEntity<Object>(getAllJobs, HttpStatus.OK);
		} else {
			logger.debug("incoming request for findAllEmployees EmployeeController ::::" + "Invalid Employee request");
			response.setTimestamp(LocalDateTime.now());
			response.setStatusCode("422");
			response.setStatusMessage("Invalid Employee request");
			return new ResponseEntity<Object>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@GetMapping(value = "/getEmployeeByPagination/{pageNo}/{pageSize}", produces = { "application/json",
			"application/xml" })
	public ResponseEntity<Object> findAllEmployeesByPagination(@PathVariable int pageNo, @PathVariable int pageSize) {
		if (pageNo >= 0 && pageSize <= 30) {
			ResponseEntity<Object> findEmployeesByPagination = employeeService.findEmployeesByPagination(pageNo,
					pageSize);
			logger.debug("response for findEmployeesByPagination EmployeeController ::::" + pageNo + "," + pageSize);
			return new ResponseEntity<Object>(findEmployeesByPagination, HttpStatus.OK);
		} else {
			logger.debug("incoming request for findEmployeesByPagination EmployeeController ::::"
					+ "Invalid Employee request for Pagination ");
			response.setTimestamp(LocalDateTime.now());
			response.setStatusCode("422");
			response.setStatusMessage("Invalid Employee request");
			return new ResponseEntity<Object>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

}
