package com.ojas.springbooot.project.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ojas.springbooot.project.model.Employee;
import com.ojas.springbooot.project.service.EmployeeService;

public class TestEmployeeController {
	@InjectMocks
	private EmployeeController employeeController;
	
	@Mock
	private EmployeeService employeeService;
	
	@BeforeEach
    public void init() { 
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testSave() {
		Employee emp1= new Employee();
		emp1.setEmpId(1);
		emp1.setEmpName("ssss");
		emp1.setPhoneNumber(3423432);
		emp1.setCity("Hyd");
		ResponseEntity<Object> emp = new ResponseEntity<Object>(HttpStatus.OK);
		when(employeeService.save(emp1)).thenReturn(emp);
		ResponseEntity<Object> save = employeeController.save(emp1);
		assertEquals(save.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void testSave_Conflict() {
		Employee emp1= null;
		ResponseEntity<Object> emp = new ResponseEntity<Object>(HttpStatus.CONFLICT);
		when(employeeService.save(emp1)).thenReturn(emp);
		ResponseEntity<Object> save = employeeController.save(emp1);
		assertEquals(save.getStatusCode(), HttpStatus.CONFLICT);
	}
	
	@Test
	public void testGetAll() throws Exception {
		ResponseEntity<Object> value = new ResponseEntity<Object>(HttpStatus.OK);
		when(employeeService.getAllEmployees(2, 15)).thenReturn(value);
		ResponseEntity<Object> all = employeeController.getAll(2, 15);
		assertEquals(all.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void testGetById() {
		ResponseEntity<Object> value= new ResponseEntity<Object>(HttpStatus.OK);
		when(employeeService.getByEmpId(1)).thenReturn(value);
		ResponseEntity<Object> byId = employeeController.getById(1);
		assertEquals(byId.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void testDeleteById() {
		when(employeeService.getByEmpId(1)).thenReturn(null);
		ResponseEntity<Object> deleteEmployee = employeeController.delete(1);
		assertEquals(deleteEmployee.getStatusCode(), HttpStatus.OK);
	}

}
