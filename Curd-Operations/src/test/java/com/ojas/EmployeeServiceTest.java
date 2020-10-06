package com.ojas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ojas.exception.CustomException;
import com.ojas.model.Employee;
import com.ojas.repositories.EmployeeRepository;
import com.ojas.response.Response;
import com.ojas.service.impl.EmployeeServiceImpl;

public class EmployeeServiceTest {
	@InjectMocks
	private EmployeeServiceImpl empServiceImpl;

	@Mock
	private EmployeeRepository empRepo;

	@Mock
	Response response;

	@Mock
	Employee employee;
	@Mock
	private ResponseEntity<Object> entity;

	@Mock
	private CustomException cex;

	@Mock
	private PageRequest pagerequest;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(empServiceImpl).build();
	}

	@Test 
	public void nullRequestTest() throws Exception {
		Employee employee = null;
		when(empRepo.save(employee)).thenReturn(employee); 
		ResponseEntity<Object> saveEmployee = empServiceImpl.saveEmployee(employee);
		assertEquals(saveEmployee.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Test
	public void checkAllEmptyTest() throws Exception {
		Employee employee = new Employee();
		employee.setName("");
		when(empRepo.save(employee)).thenThrow(CustomException.class);
		ResponseEntity<Object> saveEmployee = empServiceImpl.saveEmployee(employee);
		assertEquals(saveEmployee.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Test
	public void idNullTest() throws Exception {
		Employee emp = new Employee();
		emp.setId(null);
		when(empRepo.save(emp)).thenThrow(new CustomException(null));
		ResponseEntity<Object> saveEmployee = empServiceImpl.updateEmployee(emp);
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
		ResponseEntity<Object> saveEmployee = empServiceImpl.saveEmployee(emp);
		assertEquals(saveEmployee.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void updateEmpTest() throws Exception {
		Employee emp = new Employee();
		emp.setId(1);
		Employee findById = empRepo.findById(1).orElse(emp);
		findById.setName(emp.getName());
		findById.setCity(emp.getCity());
		findById.setPhone(emp.getPhone());
		findById.setSalary(emp.getSalary());
		when(empRepo.findById(1)).thenReturn(Optional.of(findById));
		when(empRepo.save(emp)).thenReturn(findById);
		ResponseEntity<Object> updateEmployee = empServiceImpl.updateEmployee(findById);
		assertEquals(updateEmployee.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void deleteIdNullTest() throws Exception {
		doThrow(new CustomException("record not found")).when(empRepo).deleteById(null);
		ResponseEntity<Object> saveEmployee = empServiceImpl.deleteEmployee(null);
		assertEquals(saveEmployee.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Test
	public void deleteSuccessTest() throws Exception {
		when(empRepo.findById(1)).thenReturn(null);
		ResponseEntity<Object> deleteEmployee = empServiceImpl.deleteEmployee(1);
		assertEquals(deleteEmployee.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void getIdSuccessTest() throws Exception {
		Employee emp = new Employee();
		when(empRepo.findById(1)).thenReturn(Optional.of(emp));
		ResponseEntity<Object> byIdEmployee = empServiceImpl.getByIdEmployee(1);
		assertEquals(byIdEmployee.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void getIdNullTest() throws Exception {
		when(empRepo.findById(null)).thenReturn(Optional.ofNullable(null));
		ResponseEntity<Object> byIdEmployee = empServiceImpl.getByIdEmployee(null);
		assertEquals(byIdEmployee.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Test
	public void getAllEmptyTest() throws Exception {
		List<Employee> emplist = new ArrayList<>();
		when(empRepo.findAll()).thenReturn(emplist);
		@SuppressWarnings("unchecked")
		Page<Employee> tasks = Mockito.mock(Page.class);
		Mockito.when(empRepo.findAll(org.mockito.Matchers.isA(Pageable.class))).thenReturn(tasks);
		ResponseEntity<Object> allEmployees = empServiceImpl.getAllEmployees(0, 1);
		assertEquals(allEmployees.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@Test
	public void getAllSuccessTest() throws Exception { 
		Employee emp = new Employee();
		emp.setId(1);
		emp.setName("Ojas");
		emp.setCity("HYD");
		emp.setPhone(1234567890);
		emp.setSalary(100000);
		List<Employee> emplist = new ArrayList<Employee>();
		emplist.add(emp);
		Page<Employee> pageEmp = new PageImpl<>(emplist);
		when(empRepo.findAll(org.mockito.Matchers.isA(Pageable.class))).thenReturn(pageEmp);
		ResponseEntity<Object> allEmployees = empServiceImpl.getAllEmployees(0, 1);
		assertEquals(allEmployees.getStatusCode(), HttpStatus.OK);
	}
}
