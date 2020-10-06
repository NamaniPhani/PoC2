package com.ojas.poc.junitest;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ojas.poc.controller.EmployeeController;
import com.ojas.poc.exception.PaginationResponse;
import com.ojas.poc.exception.RecordNotFoundException;
import com.ojas.poc.model.Employee;
import com.ojas.poc.response.Response;
import com.ojas.poc.service.EmployeeService;

public class EmployeeControllerTest {

	@InjectMocks
	private EmployeeController employeeController;

	@Spy
	Employee employee = new Employee();

	@Mock
	private EmployeeService employeeService;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void employeeSave() {
		Response response = mock(Response.class);
		employee.setId(1);
		employee.setName("test");
		employee.setCity("mumbai");
		employee.setPhone(1234);
		employee.setSal(1200);
		when(employeeService.saveEmployee(employee)).thenReturn(response);
		Response saveEmployee = employeeController.saveEmployee(employee);
		Integer status = saveEmployee.getStatus();
		assertEquals(response.getStatus(), status);
	}

	@Test
	public void employeeSaveConflict() {
		Response response = mock(Response.class);
		when(employeeService.saveEmployee(employee)).thenReturn(response);
		Response saveEmployee = employeeController.saveEmployee(employee);
		Integer status = saveEmployee.getStatus();
		assertEquals(response.getStatus(), status);
	}

	@Test
	public void getEmployeeById() throws RecordNotFoundException {
		employee.setId(1);
		when(employeeService.getById(employee.getId())).thenReturn(employee);
		ResponseEntity<Employee> employeeById = employeeController.getEmployeeById(employee.getId());
		HttpStatus statusCode = employeeById.getStatusCode();
		assertEquals(statusCode, HttpStatus.OK);
	}

	@Test
	public void getEmployeeByIdConflict() throws RecordNotFoundException {
		employee.setId(0);
		when(employeeService.getById(null)).thenReturn(null);
		ResponseEntity<Employee> employeeById = employeeController.getEmployeeById(null);
		HttpStatus statusCode = employeeById.getStatusCode();
		assertEquals(statusCode, HttpStatus.CONFLICT);
	}

	@Test
	public void updateEmployee() {
		when(employeeService.updateEmployee(employee)).thenReturn(employee);
		ResponseEntity<Object> updateEmployee = employeeController.updateEmployee(employee);
		HttpStatus statusCode = updateEmployee.getStatusCode();
		assertEquals(statusCode, HttpStatus.OK);
	}

	@Test
	public void updateEmployeeConflict() {
		when(employeeService.updateEmployee(null)).thenReturn(null);
		ResponseEntity<Object> updateEmployee = employeeController.updateEmployee(employee);
		HttpStatus statusCode = updateEmployee.getStatusCode();
		assertEquals(statusCode, HttpStatus.CONFLICT);
	}

	@Test
	public void deleteEmployee() throws RecordNotFoundException {
		employee.setId(1);
		employee.setName("ramesh");
		employee.setCity("Hyd");
		employee.setPhone(1233);
		employee.setSal(12000);
		employeeService.deleteEmployee(employee.getId());
		ResponseEntity<Object> updateEmployee = employeeController.updateEmployee(employee);
		assertNotNull(updateEmployee);
	}

	@Test
	public void deleteEmployeeConflict() throws Exception { 
		employee.setId(0);
		employeeService.deleteEmployee(employee.getId());
		ResponseEntity<Object> updateEmployee = employeeController.updateEmployee(null);
	//	HttpStatus statusCode = updateEmployee.getStatusCode();
		assertEquals(updateEmployee.getStatusCode(),HttpStatus.CONFLICT);
	}

	@Test
	public void getAll() {
		PaginationResponse paginationresponse = new PaginationResponse();
		paginationresponse.setPageNo(0);
		paginationresponse.setPageSize(4);
		paginationresponse.setTotalRecord(10L);
		when(employeeService.getAll(paginationresponse)).thenReturn(paginationresponse);
		ResponseEntity<Object> all = employeeController.getAll(paginationresponse);
		HttpStatus statusCode = all.getStatusCode();
		assertEquals(statusCode, HttpStatus.OK);
	}

	@Test
	public void getAllBadRequest() {
		PaginationResponse paginationresponse = new PaginationResponse();
		paginationresponse.setPageNo(null);
		paginationresponse.setPageSize(null);
		paginationresponse.setTotalRecord(null);
		when(employeeService.getAll(paginationresponse)).thenReturn(null);
		ResponseEntity<Object> all = employeeController.getAll(paginationresponse);
		HttpStatus statusCode = all.getStatusCode();
		assertEquals(statusCode, HttpStatus.BAD_REQUEST);
	}
}
