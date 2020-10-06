package com.poc5.controllerTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.poc5.controller.EmployeeController;
import com.poc5.model.Employee;
import com.poc5.response.EmployeeResponse;
import com.poc5.response.PaginationResponse;
import com.poc5.service.EmployeeService;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

	@InjectMocks
	private EmployeeController employeeController;

	@Mock
	private EmployeeService employeeService;

	@Spy
	private EmployeeResponse response;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
	}

	public List<Employee> getEmployee() {
		List<Employee> employees = new ArrayList<>();
		Employee employee = new Employee();
		employee.setId(16);
		employee.setEmpName("emp-01");
		employee.setSalary(20000);
		employee.setCity("HYD");
		employee.setPhoneNumber(1234);
		employee.setStatus("0");
		employees.add(employee);
		return employees;
	}

	public List<Employee> getEmployee_negative() {
		List<Employee> employees = new ArrayList<>();
		Employee employee = new Employee();
		employee.setId(16);
		employee.setEmpName("emp-01");
		employee.setSalary(20000);
		employee.setCity("HYD");
		employee.setPhoneNumber(0);
		employee.setStatus("0");
		employees.add(employee);
		return employees;
	}

	@Test
	public void createEmployeeTest_positive() {
		response.setEmplist(getEmployee());
		response.setStatusCode("200");
		response.setStatusMessage("Employee inserted Successfully");
		when(employeeService.createEmployee(getEmployee().get(0))).thenReturn(response);
		ResponseEntity<Object> createEmployee = employeeController.createEmployee(getEmployee().get(0));
		assertEquals(createEmployee.getStatusCode(), HttpStatus.OK);
	}

//	@Test
//	public void createEmployeeTest_negative() {
//		response.setEmplist(getEmployee());
//		response.setStatusCode("200");
//		response.setStatusMessage("Employee inserted Successfully");
//		when(employeeService.createEmployee(getEmployee().get(0))).thenReturn(response);
//		ResponseEntity<Object> createEmployee = employeeController.createEmployee(getEmployee().get(0));
//		assertEquals(createEmployee.getStatusCode(), HttpStatus.OK);
//	}

	@Test
	public void getEmployeeByIdTest() {
		response.setEmplist(getEmployee());
		response.setStatusCode("200");
		response.setStatusMessage("Employee retrieved successfully");
		when(employeeService.getEmployeeById(16)).thenReturn(response);
		ResponseEntity<Object> employeeById = employeeController.getEmployeeById(16);
		assertEquals(employeeById.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void getEmployeeByIdTest_negative() {
		response.setEmplist(null);
		response.setStatusCode("422");
		response.setStatusMessage("Invalid Employee request");
		when(employeeService.getEmployeeById(-16)).thenReturn(response);
		ResponseEntity<Object> employeeById = employeeController.getEmployeeById(-16);
		assertEquals(employeeById.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Test
	public void updateEmployeeTest() {
		response.setEmplist(getEmployee());
		response.setStatusCode("200");
		response.setStatusMessage("Employee updated successfully");
		when(employeeService.updateEmployee(getEmployee().get(0))).thenReturn(response);
		ResponseEntity<Object> updateEmployee = employeeController.updateEmployee(getEmployee().get(0));
		assertEquals(updateEmployee.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void updateEmployeeTest_negative() {
		response.setEmplist(null);
		response.setStatusCode("422");
		response.setStatusMessage("Invalid Employee request");
		when(employeeService.updateEmployee(getEmployee_negative().get(0))).thenReturn(response);
		ResponseEntity<Object> updateEmployee = employeeController.updateEmployee(getEmployee_negative().get(0));
		assertEquals(updateEmployee.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Test
	public void deleteEmployeeTest() {
		response.setEmplist(getEmployee());
		response.setStatusCode("200");
		response.setStatusMessage("Employee deleted successfully");
		when(employeeService.deleteEmployee(17)).thenReturn(response);
		ResponseEntity<Object> deleteEmployee = employeeController.deleteEmployee(17);
		assertEquals(deleteEmployee.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void deleteEmployeeTest_negative() {
		response.setEmplist(getEmployee());
		response.setStatusCode("422");
		response.setStatusMessage("Invalid Employee request");
		when(employeeService.deleteEmployee(-17)).thenReturn(response);
		ResponseEntity<Object> deleteEmployee = employeeController.deleteEmployee(-17);
		assertEquals(deleteEmployee.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Test
	public void getAllEmployeesTest() {
		PaginationResponse pageResponse = new PaginationResponse();
		pageResponse.setPageNo(0);
		pageResponse.setPageSize(1);
		pageResponse.setTotalRecord(1);
		pageResponse.setObj(getEmployee());
		when(employeeService.getAllEmployees(0, 1)).thenReturn(pageResponse);
		ResponseEntity<Object> allEmployees = employeeController.getAllEmployees(0, 1);
		assertEquals(allEmployees.getStatusCode(), HttpStatus.OK);
	}

}
