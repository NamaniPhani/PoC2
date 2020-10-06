package com.ojas.springbooot.project.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ojas.springbooot.project.dao.EmployeRepository;
import com.ojas.springbooot.project.exception.CustomException;
import com.ojas.springbooot.project.model.Employee;
import com.ojas.springbooot.project.util.Response;

public class TestEmployeeService {

	@InjectMocks
	private EmployeeServiceImpl employeeserviceImpl;
	
	@Mock
	private EmployeRepository employeRepository;
	
	@BeforeEach
    public void init() { 
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testSave_OK() {
		Employee emp= new Employee();
		emp.setEmpId(1);
		emp.setEmpName("aaa");
		emp.setPhoneNumber(333);
		emp.setCity("bang");
		when(employeRepository.save(emp)).thenReturn(emp);
		ResponseEntity<Object> save = employeeserviceImpl.save(emp);
		assertEquals(save.getStatusCode(), HttpStatus.OK);
	}
	@Test
	public void testSaveException() {
		Employee emp=null;
		when(employeRepository.save(emp)).thenThrow(CustomException.class);
		ResponseEntity<Object> save = employeeserviceImpl.save(emp);
		assertEquals(save.getStatusCode(), HttpStatus.CONFLICT);
	}
	
	@Test
    public void getAllSuccessTest() throws Exception {
        Employee emp = new Employee();
        emp.setEmpId(1);
        emp.setEmpName("sss");
        emp.setCity("HYD");
        emp.setPhoneNumber(1234567890);
        emp.setSalary(100000);
        List<Employee> emplist = new ArrayList<Employee>();
        emplist.add(emp);
        Page<Employee> pageEmp = new PageImpl<>(emplist);
        when(employeRepository.findAll(org.mockito.Matchers.isA(Pageable.class))).thenReturn(pageEmp);
        ResponseEntity<Object> allEmployees = employeeserviceImpl.getAllEmployees(0, 1);
        assertEquals(allEmployees.getStatusCode(), HttpStatus.OK);
    }
	@Test
    public void getAllFailureTest() throws Exception {
        Employee emp = new Employee();
        emp.setEmpId(1);
        emp.setEmpName("sss");
        emp.setCity("HYD");
        emp.setPhoneNumber(1234567890);
        emp.setSalary(100000);
        List<Employee> emplist = new ArrayList<>();
        emplist.add(emp);
        @SuppressWarnings("unchecked")
        Page<Employee> tasks = Mockito.mock(Page.class);
        when(employeRepository.findAll()).thenReturn(emplist);
        Mockito.when(employeRepository.findAll(org.mockito.Matchers.isA(Pageable.class))).thenReturn(tasks);
        when(employeRepository.findAll()).thenReturn(emplist);
        ResponseEntity<Object> allEmployees = employeeserviceImpl.getAllEmployees(0, 1);
        assertEquals(allEmployees.getStatusCode(), HttpStatus.CONFLICT);
    }
	
	@Test
	public void testGetById() {
		Optional<Employee> emp = null;
		when(employeRepository.findById(2)).thenReturn(emp);
		ResponseEntity<Object> byEmpId = employeeserviceImpl.getByEmpId(2);
		assertEquals(byEmpId.getStatusCode(), HttpStatus.CONFLICT);
	}
	
	@Test
	public void testGetById_Success() {
		Employee employee= new Employee();
		Optional<Employee> emp= Optional.of(employee);
		when(employeRepository.findById(2)).thenReturn(emp);
		ResponseEntity<Object> byEmpId = employeeserviceImpl.getByEmpId(2);
		assertEquals(byEmpId.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void testDelete_Conflict() {
		Response res= mock(Response.class);
		doThrow(new CustomException(409, "Invalid data")).when(employeRepository).deleteById(0);
		ResponseEntity<Object> delete = employeeserviceImpl.delete(0);
		assertEquals(delete.getStatusCode(), HttpStatus.CONFLICT);
	}
	
    @Test
    public void deleteSuccessTest() throws Exception {
        when(employeRepository.findById(1)).thenReturn(null);
        ResponseEntity<Object> deleteEmployee = employeeserviceImpl.delete(1);
        assertEquals(deleteEmployee.getStatusCode(), HttpStatus.OK);
    }

 }
