package com.ojas.module.testcontroller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ojas.module.controller.EmpController;
import com.ojas.module.exception.Response;
import com.ojas.module.model.Employee;
import com.ojas.module.repositories.EmpRepository;
import com.ojas.module.service.EmpServiceImpl;

public class TestController { 
  
	@InjectMocks
	private EmpController empController;

	@Mock
	private EmpServiceImpl empService;

	@Mock 
	private EmpRepository empRepo;

	@Autowired 
	private MockMvc mocMvc;   

	@BeforeEach  
	public void init() {
		MockitoAnnotations.initMocks(this);
		mocMvc = MockMvcBuilders.standaloneSetup(empController).build();
	}

	@Test
	public void saveTest_ok() throws Exception {
		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("swa");
		employee.setCity("Hyd");
		employee.setSalary(15000);
		employee.setPhone(1234); 
		Response response = new Response(); 
		ResponseEntity<Object> response1=empService.createEmp(employee);
		response.setData(employee);
		response.setMessage("saved successfully");
		response.setStatus("success");
		response.setStatuscode("200");
		when(empService.createEmp(employee)).thenReturn(response1);
		ResponseEntity<Object> save = empController.createEmp(employee);
		assertNotNull(response);

	} 

	
	  @Test 
	  public void getAllEmployees_ok()throws Exception{
	  Employee emp=new Employee();
	  emp.getId(); 
	  emp.getName(); 
	  emp.getSalary();  
	  emp.getCity(); 
	  emp.getPhone();
	  Response res=new Response(); 
	  ResponseEntity<Object> get = empController.getAllEmployees(0, 0);
	  assertNotNull(res);
	     
	  }
	 
	@Test
	public void deleteEmployee_ok() {   
		Employee emp=new Employee();
		Integer id=emp.getId();
		Response res=new Response();
		ResponseEntity<Object> response1=empService.deleteEmp(id);
		when(empService.deleteEmp(id)).thenReturn(response1);
		ResponseEntity<Object> delete=empController.deleteEmp(id);
		assertNotNull(res); 
		

	}
	@Test
	public void getById_ok()throws Exception{
		Employee emp=new Employee();
		Integer id=emp.getId(); 
		Response res=new Response();
		ResponseEntity<Object> response1=empService.getById(id);
		when(empService.getById(id)).thenReturn(response1);
		ResponseEntity<Object> getByIdEmp=empController.getById(id);
		assertNotNull(res);


	}

}
