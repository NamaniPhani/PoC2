package com.ojas.task.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ojas.task.model.Employee;
import com.ojas.task.model.Response;
import com.ojas.task.service.EmployeeService;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

	@InjectMocks
	EmployeeController controller;

	@Mock
	EmployeeService service;

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper mapper;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this.getClass());
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	public Employee getEmployee() {
		Employee emp = new Employee();
		emp.setId(1);
		emp.setName("nagarani");
		emp.setPhone(9827785412l);
		emp.setSal(40000);
		emp.setCity("Guntur");
		return emp;
	}

	@Test
	public void testSaveOrUpdateOk() {
		Employee employee = getEmployee();
		Response response = new Response(employee, "Employee Saved Sucessfully", "200");

		when(service.save(employee)).thenReturn(response);
		ResponseEntity<?> savedResponse = controller.save(employee);
		assertEquals(HttpStatus.OK, savedResponse.getStatusCode());
	}

	@Test
	public void testSaveOrUpdateConflict() {
		Employee employee = null;
		ResponseEntity<?> savedResponse = controller.save(employee);
		assertEquals(HttpStatus.CONFLICT, savedResponse.getStatusCode());
	}

	@Test
	public void testGetByIdOk() {
		Employee employee = getEmployee();
		Response response = new Response(employee, "Record Found", "200");
		when(service.findById(1)).thenReturn(response);
		ResponseEntity<Object> actualResponse = controller.getById(1);
		assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
	}

	@Test
	public void testGetByIdConflict() {
		ResponseEntity<Object> actualResponse = controller.getById(null);
		assertEquals(HttpStatus.CONFLICT, actualResponse.getStatusCode());
	}

	@Test
	public void testGetallOk() {
		List<Employee> list = new ArrayList<Employee>();
		list.add(getEmployee());
		Response response = new Response(list, "Records Found", "200");
		when(service.findAll(0, 5)).thenReturn(response);
		ResponseEntity<Object> responseEntity = controller.getall(0, 5);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

	}

	@Test
	public void testGetallConflict() {
		ResponseEntity<Object> responseEntity = controller.getall(null, null);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

	}

	@Test
	public void testDeleteOk() {
		Response response = new Response(1, "Sucessfully Deleted Employee With Id ", "200");
		when(service.delete(1)).thenReturn(response);
		ResponseEntity<Object> actualResponse = controller.delete(1);
		assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
	}

	@Test
	public void testDeleteConflict() {
		ResponseEntity<Object> response = controller.delete(null);
		assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
	}

	@Test
	public void testSave_Conflict() throws Exception {
		Response res = new Response();
		res.setObject(getEmployee());
		res.setStatus("409");
		when(service.save(Mockito.any(Employee.class))).thenReturn(res);
		RequestBuilder b = MockMvcRequestBuilders.post("/save").accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(res.getObject()));

		MvcResult result = mvc.perform(b).andReturn();
		assertEquals(HttpStatus.BAD_REQUEST.value(), result.getResponse().getStatus());

	}

}
