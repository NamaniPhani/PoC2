package com.poc.controllertest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.poc.controller.EmpController;
import com.poc.model.Employee;
import com.poc.repository.EmpRepository;
import com.poc.response.PagenationResponse;
import com.poc.response.Response;
import com.poc.service.EmpServiceImpl;

@RunWith(MockitoJUnitRunner.class)

public class EmpControllerTest {
	@InjectMocks
	private EmpController empController;

	@Mock
	private EmpServiceImpl empService;

	@Mock
	private EmpRepository empRepository;

	/* To avoid side effect between tests we use @Before */

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(empController.getClass());
	}

@Spy
	Employee employee = new Employee();
@Spy	
Response response = new Response();

	@Test
	public void saveTest() {
		employee = new Employee();
		employee.setId(1);
		employee.setName("naveen");
		employee.setPhone(1234);
		employee.setSalary(20000);
		employee.setCity("hyderabad");
		response.setStatusCode("200");
		response.setMessage("Success");
		response.setEmpList(employee);
		when(empService.saveOrUpdate(employee)).thenReturn(response);
		ResponseEntity<Object> response1 = empController.saveOrUpdate(employee);
		assertEquals(response1.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void save_Conflict() {
		ResponseEntity<Object> response1 = empController.saveOrUpdate(null);
		assertEquals(response1.getStatusCode(), HttpStatus.BAD_REQUEST);
	}
	

	@Test
	public void readByIdTest() {
		employee = new Employee();
		employee.setId(1);
		Integer i = 1;
		response = new Response();
		response.setStatusCode("200");
		response.setMessage("success");
		response.setEmpList(employee);
		when(empService.readById(i)).thenReturn(response);
		ResponseEntity<Object> readById = empController.readById(i);
		assertEquals(readById.getStatusCode(), HttpStatus.OK);
	}
@Test
	public void readByIdTest_Conflict(){
		ResponseEntity<Object> response1 = empController.readById(null);
		assertEquals(response1.getStatusCode(), HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void readAllTest() {
		PagenationResponse pr = new PagenationResponse();
		pr.setStatusCode("200");
		pr.setMessage("success");
		pr.setPageNo(0);
		pr.setPageSize(2);
		pr.setEmpList(employee);
		when(empService.readAll(pr)).thenReturn(pr);
		ResponseEntity<Object> readAll = empController.readAll(pr);
		assertEquals(readAll.getStatusCode(), HttpStatus.OK);

	}

	@Test
	public void readAllTest_Conflict(){
		ResponseEntity<Object> response1 = empController.readAll(null);
		assertEquals(response1.getStatusCode(), HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void deleteTest() {
		employee = new Employee();
		employee.setId(1);
		Response delete = empService.delete(1);
		when(empService.delete(1)).thenReturn(delete);
		ResponseEntity<Object> delete2 = empController.delete(1);
		assertEquals(delete2.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void deleteTest_Conflict(){
	ResponseEntity<Object> delete = empController.delete(null);
		assertEquals(delete.getStatusCode(), HttpStatus.BAD_REQUEST);
	}
}