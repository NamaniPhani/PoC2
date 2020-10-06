package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.demo.example.controller.EmployeeController;
import com.demo.example.entity.Employee;
import com.demo.example.response.Response;
import com.demo.example.service.EmployeeService;

public class TestEmployeeController {

	@InjectMocks
	private EmployeeController employeeController;

	@Mock
	private EmployeeService employeeService;

	@Spy
	Employee employee;

	@Spy
	Response response;

	@Mock
	ResponseEntity<Object> responseEntityOK = new ResponseEntity<Object>(HttpStatus.OK);

	@Autowired
	private MockMvc mockMvc;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
		
	}

	public List<Employee> employeeDetails() {
		List<Employee> listofEmployees = new ArrayList<>();
		Employee emp1 = new Employee();
		emp1.setEmployeeId(1);
		emp1.setEmployeeName("Rama");
		emp1.setEmployeeSalary(7897);
		emp1.setEmployeeCity("Banglore");
		emp1.setEmployeePhoneNumber(979879);
		Employee emp2 = new Employee();
		emp2.setEmployeeId(2);
		emp2.setEmployeeName("Krishna");
		emp2.setEmployeeSalary(54654);
		emp2.setEmployeeCity("Banglore");
		emp2.setEmployeePhoneNumber(489461);
		listofEmployees.add(emp1);
		listofEmployees.add(emp2);
		return listofEmployees;
	}

	@Test
	public void postEmployeeIfBlockTest() {
		Employee emp1 = new Employee();
		emp1.setEmployeeId(1);
		emp1.setEmployeeName("Rama");
		emp1.setEmployeeSalary(7897);
		emp1.setEmployeeCity("Banglore");
		emp1.setEmployeePhoneNumber(979879);
		response.setListofEmployees(employeeDetails());
		when(employeeService.createEmployee(emp1)).thenReturn(responseEntityOK);
		ResponseEntity<Object> saveAllData = employeeController.postEmployee(emp1);
		assertEquals(HttpStatus.OK, saveAllData.getStatusCode());
	}

//	@Test
//	public void postEmployeeElseBlockNullTest() {
//		Employee emp1 = new Employee();
//		emp1.setEmployeeId(null);
//		emp1.setEmployeeName(null);
//		emp1.setEmployeeSalary(null);
//		emp1.setEmployeeCity(null);
//		emp1.setEmployeePhoneNumber(null);
//		ResponseEntity<Object> saveAllData = employeeController.postEmployee(emp1);
//		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, saveAllData.getStatusCode());
//	}

	@Test
	public void postEmployeeElseBlockZeroTest() {
		Employee emp1 = new Employee();
		emp1.setEmployeeId(-1);
		emp1.setEmployeeName("");
		emp1.setEmployeeSalary(-4);
		emp1.setEmployeeCity("");
		emp1.setEmployeePhoneNumber(-1);
		ResponseEntity<Object> saveAllData = employeeController.postEmployee(emp1);
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, saveAllData.getStatusCode());
	}

	@Test
	public void postEmployeeElseBlockObjectTest() {
		Employee emp1 = null;
		ResponseEntity<Object> saveAllData = employeeController.postEmployee(emp1);
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, saveAllData.getStatusCode());
	}

	@Test
	public void getEmployeeByIdIfBlockTest() throws Exception {
		when(employeeService.findEmployeeById(1)).thenReturn(responseEntityOK);
		ResponseEntity<Object> getByIdEmployee = employeeController.findEmployeeById(1);
		assertEquals(HttpStatus.OK, getByIdEmployee.getStatusCode());
	}

	@Test
	public void getEmployeeByIdElseBlockTest() throws Exception {
		ResponseEntity<Object> getByIdEmployee = employeeController.findEmployeeById(-1);
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, getByIdEmployee.getStatusCode());
	}

	@Test
	public void findAllEmployeesTest() throws Exception {
		when(employeeService.findAllEmployees()).thenReturn(responseEntityOK);
		ResponseEntity<Object> getAllEmployees = employeeController.findAllEmployees();
		assertEquals(HttpStatus.OK, getAllEmployees.getStatusCode());
	}

	@Test
	public void updateEmployeeByIdIfBlockTest() throws Exception {
		Employee emp1 = new Employee();
		emp1.setEmployeeId(1);
		emp1.setEmployeeName("Rama");
		emp1.setEmployeeSalary(7897);
		emp1.setEmployeeCity("Banglore");
		emp1.setEmployeePhoneNumber(979879);
		when(employeeService.UpdateEmployee(emp1, emp1.getEmployeeId())).thenReturn(responseEntityOK);
		ResponseEntity<Object> updateEmployee = employeeController.UpdateEmployee(emp1, emp1.getEmployeeId());
		assertEquals(HttpStatus.OK, updateEmployee.getStatusCode());
	}

	@Test
	public void updateEmployeeByIdElseBlockObjectTest() throws Exception {
		Employee emp1 = null;
		ResponseEntity<Object> updateEmployee = employeeController.UpdateEmployee(emp1, -8);
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, updateEmployee.getStatusCode());
	}
	
	
	@Test
	public void updateEmployeeByIdElseBlockIdTest() throws Exception {
		Employee emp1 = new Employee();
		emp1.setEmployeeId(-11);
		emp1.setEmployeeName("Rama");
		emp1.setEmployeeSalary(7897);
		emp1.setEmployeeCity("Banglore");
		emp1.setEmployeePhoneNumber(979879);
		ResponseEntity<Object> updateEmployee = employeeController.UpdateEmployee(emp1, -8);
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, updateEmployee.getStatusCode());
	}

	@Test
	public void deleteEmployeeByIdIfBlockTest() throws Exception {
		when(employeeService.deleteEmployeeById(1)).thenReturn(responseEntityOK);
		ResponseEntity<Object> deleteByIdEmployee = employeeController.deleteEmployeeById(1);
		assertEquals(HttpStatus.OK, deleteByIdEmployee.getStatusCode());
	}

	@Test
	public void deleteEmployeeByIdElseBlockTest() throws Exception {
		ResponseEntity<Object> deleteByIdEmployee = employeeController.deleteEmployeeById(-1);
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, deleteByIdEmployee.getStatusCode());
	}

	@Test
	public void findEmployeeByPaginationIfBlockTest() throws Exception {
		when(employeeService.findEmployeesByPagination(0, 1)).thenReturn(responseEntityOK);
		ResponseEntity<Object> getAllEmployees = employeeController.findAllEmployeesByPagination(0, 1);
		assertEquals(HttpStatus.OK, getAllEmployees.getStatusCode());
	}

	@Test
	public void findEmployeeByPaginationElseBlockTest() throws Exception {
		when(employeeService.findEmployeesByPagination(-1, 50)).thenReturn(responseEntityOK);
		ResponseEntity<Object> getAllEmployees = employeeController.findAllEmployeesByPagination(-1, 50);
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, getAllEmployees.getStatusCode());
	}

}
