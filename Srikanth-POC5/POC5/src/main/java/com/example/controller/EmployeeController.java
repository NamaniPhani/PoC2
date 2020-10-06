package com.example.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Employee;
import com.example.response.ErrorResponse;
import com.example.response.SuccessResponse;
import com.example.service.EmployeeService;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	ErrorResponse errorResponse = new ErrorResponse();
	Logger logger = Logger.getLogger(this.getClass());

	@PostMapping("/save")
	public ResponseEntity<Object> saveEmployee(@RequestBody Employee employee) {
		try {
			 if (employee.getName() != null && !employee.getName().isEmpty() && employee.getCity() != null
					&& !employee.getCity().isEmpty() && employee.getSalary() != null && employee.getSalary() != 0
					&& employee.getPhonenumber() != null) 
			{

				SuccessResponse saveEmployee = employeeService.saveEmployee(employee);
				return new ResponseEntity<Object>(saveEmployee, HttpStatus.OK);
			}
		} 
		catch (Exception e)
		{
			System.out.println("Exception Occured........");
			
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);     
		
	}

	@GetMapping("/getAll/{pageNo}/{pageSize}")
	public ResponseEntity<Object> getAllEmployeeObject(@PathVariable int pageNo, @PathVariable int pageSize) {
		SuccessResponse allEmployeeObject = employeeService.getAllEmployeeObject(pageNo, pageSize);
		int value=allEmployeeObject.getEmployeeList().size();
		if(value!=0)
		{
			return new ResponseEntity<Object>(allEmployeeObject, HttpStatus.OK);
		}
		return new ResponseEntity<Object>(allEmployeeObject, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable("id") int id) {
		if (id > 0) {
			SuccessResponse deleteById = employeeService.deleteById(id);
			return new ResponseEntity<Object>(deleteById, HttpStatus.OK);
		}
		errorResponse.setStatusCode("422");
		errorResponse.setStatusMessage("Invalid Employee request");
		return new ResponseEntity<Object>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@PutMapping("/edit")
	public ResponseEntity<Object> editById(@RequestBody Employee employee) {
		SuccessResponse editById = employeeService.edit(employee);
		return new ResponseEntity<Object>(editById, HttpStatus.OK);
	}
}
