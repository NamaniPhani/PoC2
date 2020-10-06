package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.demo.example.dao.EmployeeDAO;
import com.demo.example.entity.Employee;
import com.demo.example.response.Response;
import com.demo.example.service.EmployeeServiceImpl;

public class TestEmployeeService {

	@InjectMocks
	private EmployeeServiceImpl employeeServiceImpl;

	@Mock
	private EmployeeDAO employeeDao;

	@Spy
	Response response;

	
	@Mock
	ResponseEntity<Object> responseEntityOK = new ResponseEntity<Object>(response, HttpStatus.OK);
	
	
	@Mock
	private PageRequest pagerequest;
   
	@Autowired
	private MockMvc mockMvc;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(employeeServiceImpl).build();
		
	}

	public Employee employeeDetails() {
		Employee emp1 = new Employee();
		emp1.setEmployeeId(1);
		emp1.setEmployeeName("Rama");
		emp1.setEmployeeSalary(7897);
		emp1.setEmployeeCity("HYD"); 
		emp1.setEmployeePhoneNumber(979879);
		return emp1;
	}
	
	public List<Employee> ListOfEmployees() {
		Employee emp1 = new Employee();
		emp1.setEmployeeId(1);
		emp1.setEmployeeName("Rama");
		emp1.setEmployeeSalary(7897);
		emp1.setEmployeeCity("HYD"); 
		emp1.setEmployeePhoneNumber(979879);
		Employee emp2 = new Employee();
		emp2.setEmployeeId(1);
		emp2.setEmployeeName("Rama");
		emp2.setEmployeeSalary(7897);
		emp2.setEmployeeCity("HYD"); 
		emp2.setEmployeePhoneNumber(979879);
		List<Employee> listfoEmployees = new ArrayList<Employee>();
		listfoEmployees.add(emp1); listfoEmployees.add(emp2);
		return listfoEmployees;

	}
	
	@Test
	public void createEmployeeIfBlockTest() throws Exception {
		List<Employee> listofEmployees = new ArrayList<Employee>();
		Employee emp1 = new Employee();
		emp1.setEmployeeId(1);
		emp1.setEmployeeName("Rama");
		emp1.setEmployeeSalary(7897);
		emp1.setEmployeeCity("HYD");
		emp1.setEmployeePhoneNumber(979879);
		listofEmployees.add(emp1);
		when(employeeDao.createEmployee(emp1)).thenReturn(emp1);
		ResponseEntity<Object> createEmployee = employeeServiceImpl.createEmployee(emp1);
		assertEquals(HttpStatus.OK, createEmployee.getStatusCode());
	}
  

	@Test
	public void findAllEmployeesIfBlockTest() throws Exception {
		List<Employee> listofEmployees = new ArrayList<Employee>();
		Employee emp1 = new Employee();
		emp1.setEmployeeId(1);
		emp1.setEmployeeName("Rama");
		emp1.setEmployeeSalary(7897);  
		emp1.setEmployeeCity("HYD");
		emp1.setEmployeePhoneNumber(979879);
		Employee emp2 = new Employee();
		emp2.setEmployeeId(2);
		emp2.setEmployeeName("Krishna");
		emp2.setEmployeeSalary(54654);
		emp2.setEmployeeCity("Banglore");
		emp2.setEmployeePhoneNumber(489461);
		listofEmployees.add(emp1); listofEmployees.add(emp2);
		when(employeeDao.findAllEmployees()).thenReturn(listofEmployees);
		  ResponseEntity<Object> findAllEmployees = employeeServiceImpl.findAllEmployees();
		  assertEquals(HttpStatus.OK, findAllEmployees.getStatusCode());
	}
	
	
	@Test
	public void findAllEmployeesElseBlockTest() throws Exception {	
		 List<Employee> listofEmployees = Collections.<Employee>emptyList();
		 when(employeeDao.findAllEmployees()).thenReturn(listofEmployees);
		  ResponseEntity<Object> findAllEmployees = employeeServiceImpl.findAllEmployees();
		  assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, findAllEmployees.getStatusCode());
	}
	
	
	
	@Test
	public void findEmployeeByIdIfBlockTest() throws Exception {
		when(employeeDao.findEmployeeById(1)).thenReturn(employeeDetails());
		ResponseEntity<Object> createEmployee = employeeServiceImpl.createEmployee(employeeDetails());
		assertEquals(HttpStatus.OK, createEmployee.getStatusCode());
	}

	 
	@Test
	public void findEmployeeByIdElseBlockTest() throws Exception {		 
		when(employeeDao.findEmployeeById(-1)).thenReturn(employeeDetails());
		ResponseEntity<Object> createEmployee = employeeServiceImpl.findEmployeeById(-1);
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, createEmployee.getStatusCode());
	}
	
	
	@Test
	public void updateEmployeeIfBlockTest() throws Exception {
		Employee emp1 = new Employee();
		emp1.setEmployeeId(1);
		emp1.setEmployeeName("Rama");
		emp1.setEmployeeSalary(7897);
		emp1.setEmployeeCity("HYD");
		emp1.setEmployeePhoneNumber(979879);
		when(employeeDao.UpdateEmployee(emp1,emp1.getEmployeeId())).thenReturn(emp1);
		ResponseEntity<Object> createEmployee = employeeServiceImpl.UpdateEmployee(emp1,emp1.getEmployeeId());
		assertEquals(HttpStatus.OK, createEmployee.getStatusCode());
	}
	
	
	@Test
	public void updateEmployeeElseBlockTest() throws Exception {
		Employee emp1 =null;
		ResponseEntity<Object> createEmployee = employeeServiceImpl.UpdateEmployee(emp1,-11);
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, createEmployee.getStatusCode());
	}
	
	
	@Test
	public void deleteEmployeeByIdIfBlockTest() throws Exception {
		when(employeeDao.deleteEmployeeById(1)).thenReturn("Record Successfully deleted"); 
		ResponseEntity<Object> createEmployee = employeeServiceImpl.deleteEmployeeById(1);
		assertEquals(HttpStatus.OK, createEmployee.getStatusCode());
	}
	
	@Test
	public void deleteEmployeeByIdElseBlockTest() throws Exception {		 
		ResponseEntity<Object> createEmployee = employeeServiceImpl.deleteEmployeeById(-1);
		assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, createEmployee.getStatusCode());
	}    
	 
	@Test
	public void findEmployeeByPaginationIfBlockTest() throws Exception {
		when(employeeDao.findEmployeesByPagination(response.getListofEmployees(),-1, 50)).thenReturn(ListOfEmployees());
		ResponseEntity<Object> getAllEmployees = employeeServiceImpl.findEmployeesByPagination(-1, 50);
		assertEquals(HttpStatus.OK, getAllEmployees.getStatusCode());
	}
	 
	
}
