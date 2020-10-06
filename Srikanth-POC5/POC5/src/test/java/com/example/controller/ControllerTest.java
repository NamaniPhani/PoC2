package com.example.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.example.model.Employee;
import com.example.response.ErrorResponse;
import com.example.response.SuccessResponse;
import com.example.service.EmployeeService;

public class ControllerTest {
	@InjectMocks
	private EmployeeController employeeController;
	@Mock
	private EmployeeService employeeService;
	@Spy
	Employee employee = new Employee();
	@Spy
	ErrorResponse errorResponse = new ErrorResponse();
	@Spy
	SuccessResponse success = new SuccessResponse();
	@Spy
	ResponseEntity<Object> failure = new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
	@Spy
	SuccessResponse conflict = new SuccessResponse();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	public Employee empDetails() {
		employee.setId(11);
		employee.setName("Mahesh");
		employee.setSalary(12000);
		employee.setCity("hyderabad");
		employee.setPhonenumber("9866647339");

		return employee;
	}

	public List<Employee> resDetails() {
		List<Employee> employeeList = new ArrayList<>();
		Employee emp1 = new Employee();
		emp1.setId(1);
		emp1.setName("abc");
		emp1.setCity("hyderabad");
		emp1.setSalary(12333);
		emp1.setPhonenumber("98989898");
		Employee emp2 = new Employee();
		emp2.setId(2);
		emp2.setName("bcd");
		emp2.setCity("vijayawada");
		emp2.setSalary(13456);
		emp2.setPhonenumber("45854658");
		employeeList.add(emp1);
		employeeList.add(emp2);
		
		return employeeList;
	}

	public SuccessResponse succesDetails() 
	{
		SuccessResponse reSuccessResponse = new SuccessResponse();
		reSuccessResponse.setEmployeeList(this.resDetails());
		reSuccessResponse.setStatusCode("200");
		reSuccessResponse.setStatusMessage("Success");
		return reSuccessResponse;
	}

	@Test
	public void saveTest() {
		Employee emp = empDetails();
		when(employeeService.saveEmployee(emp)).thenReturn(success);
		ResponseEntity<Object> saveEmployee = employeeController.saveEmployee(emp);
		HttpStatus statusCode = saveEmployee.getStatusCode();
		assertEquals(HttpStatus.OK, statusCode);
	}

	@Test()
	public void exceptionTest() {
         employee = null;
		when(employeeService.saveEmployee(employee)).thenReturn(conflict);
		ResponseEntity<Object> saveAllData = employeeController.saveEmployee(employee);
		HttpStatus statusCode = saveAllData.getStatusCode();
		assertEquals(HttpStatus.CONFLICT, statusCode);
	}

	@Test
	public void getAllTest() {
		SuccessResponse reResponse = succesDetails();
		reResponse.getEmployeeList().size();
		List<Employee> emp = resDetails();
		when(employeeService.getAllEmployeeObject(0, 1)).thenReturn(reResponse);
		ResponseEntity<Object> saveEmployee = employeeController.getAllEmployeeObject(0, 1);
		HttpStatus statusCode = saveEmployee.getStatusCode();
		assertEquals(HttpStatus.OK, statusCode);
	}
	
	@Test
	public void emptyListGetAllTest() {
		SuccessResponse reResponse = success;
		int value=0;		 
		when(employeeService.getAllEmployeeObject(0, 1)).thenReturn(reResponse);
		ResponseEntity<Object> saveEmployee = employeeController.getAllEmployeeObject(0, 1);
		HttpStatus statusCode = saveEmployee.getStatusCode();
		assertNotNull(statusCode);
	}

	@Test
	public void editByIdTest() {
		Employee emp = empDetails();
		when(employeeService.edit(emp)).thenReturn(success);
		ResponseEntity<Object> saveEmployee = employeeController.editById(emp);
		HttpStatus statusCode = saveEmployee.getStatusCode();
		assertEquals(HttpStatus.OK, statusCode);
	}

	@Test
	public void deleteByIdTest() {

		when(employeeService.deleteById(1)).thenReturn(success);
		ResponseEntity<Object> saveEmployee = employeeController.deleteById(1);
		HttpStatus statusCode = saveEmployee.getStatusCode();
		assertEquals(HttpStatus.OK, statusCode);
	}

	@Test
	public void invalidDeleteByIdTest() {
		int id = 0;
		when(employeeService.deleteById(id)).thenReturn(success);
		ResponseEntity<Object> saveEmployee = employeeController.deleteById(id);
		HttpStatus statusCode = saveEmployee.getStatusCode();
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, statusCode);
	}

}