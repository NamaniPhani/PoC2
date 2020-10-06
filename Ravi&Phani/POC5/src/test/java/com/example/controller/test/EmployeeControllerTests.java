package com.example.controller.test;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.controller.EmployeeController;
import com.example.exception.CustomException;
import com.example.exception.Response;
import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import com.example.service.EmpServiceInf;

@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTests {

	@InjectMocks
	private EmployeeController employeeController;
	@Mock
	private EmpServiceInf employeeService;
	@Mock
	EmployeeRepository empRepository;
	@Spy
	Employee emp;
	
	@Test
	public void saveOrUpdateTest() {
		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("Rajendra");
		employee.setSalary(5678);
		employee.setCity("Bangalore");
		employee.setPhone(9022);
		when(employeeService.saveOrUpdate(employee) ).thenReturn(new ResponseEntity<>(employee, HttpStatus.OK) );
		ResponseEntity<Object> save = employeeController.saveOrUpdate(employee);
		assertEquals(save.getStatusCode(), HttpStatus.OK);
	}	
	
	@Test
	public void getAllTest() {
		when(employeeService.getAllEmployees(0, 1)).thenReturn(new ResponseEntity<Object>(HttpStatus.OK));
		ResponseEntity<Object> save = employeeController.getAllEmployees(0, 1);
		assertEquals(save.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void getByIdTest() {
		when(employeeService.getById(2)).thenReturn(new Response());
        Response byId = employeeController.getById(2);
		assertNotEquals(byId.getStatusCode(), HttpStatus.OK);
	}
    
	@Test
	public void deleteByIdForNullTest() {
		when(employeeService.getById(2)).thenReturn(null);
		ResponseEntity<Object> deleteById = employeeController.deleteById(2);
		assertNotEquals(deleteById.getStatusCode(), HttpStatus.OK);
	}
	@Test
	public void deleteByIdForNotNullTest() {
		when(employeeService.getById(2)).thenReturn(new Response());
		ResponseEntity<Object> deleteById = employeeController.deleteById(2);
		assertEquals(deleteById.getStatusCode(), HttpStatus.OK);
	}
	
	
}
	
		
	
   