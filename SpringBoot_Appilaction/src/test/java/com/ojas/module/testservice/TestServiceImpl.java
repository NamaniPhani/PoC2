package com.ojas.module.testservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ojas.module.controller.EmpController;
import com.ojas.module.model.Employee;
import com.ojas.module.repositories.EmpRepository;
import com.ojas.module.service.EmpServiceImpl;

public class TestServiceImpl {
	@InjectMocks 
	EmpServiceImpl empService; 

	@Mock 
	EmpRepository empRepo; 

	@Mock
	EmpController empController;

	@Autowired
	private MockMvc mocMvc;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		mocMvc = MockMvcBuilders.standaloneSetup(empService).build();
	}

	@Test
	public void saveNullTest() throws Exception {
		Employee emp = null;  
		when(empRepo.save(emp)).thenReturn(emp);
		ResponseEntity<Object> saveEmployee = empService.createEmp(emp); 
		assertEquals(saveEmployee.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY); 
		

	} 

	@Test
	public void saveEmpTest() throws Exception {
		Employee emp = new Employee();
		emp.setId(1);
		emp.setName("Ojas");
		emp.setCity("HYD");
		emp.setSalary(1000000);
		emp.setPhone(1234567890);
		when(empRepo.save(emp)).thenReturn(emp);
		ResponseEntity<Object> saveEmployee = empService.createEmp(emp);
		assertEquals(saveEmployee.getStatusCode(), HttpStatus.OK);
	}
	

	@Test
	public void deleteSuccessTest() throws Exception {
		when(empRepo.findById(1)).thenReturn(null);
		ResponseEntity<Object> deleteEmployee = empService.deleteEmp(1);
		assertEquals(deleteEmployee.getStatusCode(), HttpStatus.OK);
	}
 
	@Test
	public void getIdSuccessTest() throws Exception {
		Employee emp = new Employee();
		when(empRepo.findById(1)).thenReturn(Optional.of(emp));
		ResponseEntity<Object> byIdEmployee = empService.getById(1);
		assertEquals(byIdEmployee.getStatusCode(), HttpStatus.OK);
	}
 
	
	@Test
	public void deleteNullTest() throws Exception {
		when(empRepo.findById(1)).thenReturn(null);
		ResponseEntity<Object> deleteEmployee = empService.deleteEmp(null);
		assertEquals(deleteEmployee.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Test
	public void getAllEmptyTest() throws Exception { 
		List<Employee> emplist = new ArrayList<>();
		when(empRepo.findAll()).thenReturn(emplist);
		@SuppressWarnings("unchecked")
		Page<Employee> tasks = Mockito.mock(Page.class);
		Mockito.when(empRepo.findAll(org.mockito.Matchers.isA(Pageable.class))).thenReturn(tasks);
		ResponseEntity<Object> allEmployees = empService.getAllEmployees(0, 1);
		assertEquals(allEmployees.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void getAllSuccessTest() throws Exception {
		Employee emp = new Employee();
		emp.setId(1); 
		emp.setName("SWATHI");
		emp.setCity("HYD");
		emp.setPhone(1234567890);
		emp.setSalary(100000);
		List<Employee> emplist = new ArrayList<>();
		emplist.add(emp); 
		Page<Employee> pageEmp = new PageImpl<>(emplist);
		@SuppressWarnings("unchecked")
		Page<Employee> tasks = Mockito.mock(Page.class);
		when(empRepo.findAll(org.mockito.Matchers.isA(Pageable.class))).thenReturn(pageEmp);
		ResponseEntity<Object> allEmployees = empService.getAllEmployees(0, 1);
		assertEquals(allEmployees.getStatusCode(), HttpStatus.OK);
	}

}
