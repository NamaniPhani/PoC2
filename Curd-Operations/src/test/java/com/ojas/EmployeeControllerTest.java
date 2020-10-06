package com.ojas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ojas.controller.EmployeeController;
import com.ojas.model.Employee;
import com.ojas.service.impl.EmployeeServiceImpl;

public class EmployeeControllerTest {

	@InjectMocks
	private EmployeeController empContoller;

	@Mock
	private EmployeeServiceImpl empService;

	@Mock
	private ResponseEntity<Object> entity;

	@Autowired
	private MockMvc mockMvc;
 
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(empContoller).build();
	}

	@Test
	public void saveEmpTest() throws Exception {
		Employee emp = new Employee(); 
		emp.setId(1);
		emp.setName("Ojas");
		emp.setCity("HYD");
		emp.setSalary(1000000);
		emp.setPhone(1234567890);
		when(empService.saveEmployee(emp)).thenReturn(entity);
		ResponseEntity<Object> saveEmployee = empContoller.saveEmployee(emp);
		assertEquals(saveEmployee.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void updateEmpTest() throws Exception {
		Employee emp = new Employee();
		emp.setId(1);
		emp.setName("HashBlocks");
		emp.setCity("HYD");
		emp.setSalary(1000000);
		emp.setPhone(1234567890);
		when(empService.updateEmployee(emp)).thenReturn(entity); 
		ResponseEntity<Object> updateEmployee = empContoller.updateEmployee(emp);
		assertEquals(updateEmployee.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void deleteEmpTest() throws Exception {
		when(empService.deleteEmployee(1)).thenReturn(entity);
		ResponseEntity<Object> deleteByIdEmployee = empContoller.deleteByIdEmployee(1);
		assertEquals(deleteByIdEmployee.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void getByIdEmpTest() throws Exception {
		when(empService.getByIdEmployee(1)).thenReturn(entity); 
		ResponseEntity<Object> getByIdEmployee = empContoller.getByIdEmployee(1);
		assertEquals(getByIdEmployee.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void getAllEmpsTest() throws Exception {
		when(empService.getAllEmployees(0, 1)).thenReturn(entity);
		ResponseEntity<Object> getAllEmployees = empContoller.getAllEmployees(0, 1);
		assertEquals(getAllEmployees.getStatusCode(), HttpStatus.OK);
	}
}
