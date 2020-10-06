package com.example.service.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.exception.CustomException;
import com.example.exception.Response;
import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import com.example.service.EmployeeServiceImpl;

@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTests {

	@InjectMocks
	private EmployeeServiceImpl employeeServiceImpl;
	@Mock
	private EmployeeRepository employeeRepository;
	@Spy
	Employee emp;
	@Spy
	Pageable pag;
	
	
	@Test
	public void saveOrUpdateTest() {
		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("Manoj");
		employee.setSalary(4567);
		employee.setCity("Mumbai");
		employee.setPhone(1234);
		when(employeeRepository.save(employee)).thenReturn(employee);
		ResponseEntity<Object> saveOrUpdate = employeeServiceImpl.saveOrUpdate(employee);
		assertEquals(saveOrUpdate.getStatusCode(),HttpStatus.OK);
		
	}
	
	
	
	
	@Test
	public void getAllEmployeesTest() {
	
		Page<Employee> pagedResult = (Page<Employee>) new ArrayList<Employee>();
		when(employeeRepository.findAll(pag)).thenReturn(pagedResult);
		ResponseEntity<Object> findAll = employeeServiceImpl.getAllEmployees(0, 1);
		assertEquals(findAll.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void getById() throws CustomException {
		Optional<Employee> findById2 = employeeRepository.findById(4);
		Response findById = employeeServiceImpl.getById(4);
		assertEquals(findById.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void deleteByIdTest() {
		employeeRepository.deleteById(6);
	}
}
