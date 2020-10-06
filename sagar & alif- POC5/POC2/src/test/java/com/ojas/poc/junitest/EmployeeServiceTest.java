package com.ojas.poc.junitest;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.ojas.poc.exception.PaginationResponse;
import com.ojas.poc.exception.RecordNotFoundException;
import com.ojas.poc.model.Employee;
import com.ojas.poc.repository.EmployeeRepository;
import com.ojas.poc.response.Response;
import com.ojas.poc.service.EmployeeService;

public class EmployeeServiceTest {

	@InjectMocks
	private EmployeeService employeeService;

	@Mock
	private EmployeeRepository employeeRepository;

	@Spy
	Employee employee = new Employee();

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void employeeSave() {
		employee.setId(1);
		employee.setName("test");
		employee.setCity("mumbai");
		employee.setPhone(1234);
		employee.setSal(1200);
		when(employeeRepository.save(employee)).thenReturn(employee);
		Response saveEmployee = employeeService.saveEmployee(employee);
		assertEquals(saveEmployee.getStatus(), 200);
	}

	@Test
	public void employeeNullCheck() {
		when(employeeRepository.save(employee)).thenReturn(null);
		Response saveEmployee = employeeService.saveEmployee(employee);
		assertEquals(saveEmployee.getStatus(), 409);
	}

	@Test
	public void employeeException() {
		when(employeeRepository.save(employee)).thenThrow(Exception.class);
		Response saveEmployee = employeeService.saveEmployee(employee);
		assertEquals(saveEmployee.getStatus(), 409);
	}

	@Test
	public void getById() throws RecordNotFoundException {
		employee.setId(1);
//		Optional<Employee> empOptional = Optional.ofNullable(employee);
		when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
		Employee byId = employeeService.getById(employee.getId());
		assertEquals(byId.getId(), 1);
	}

	@Test
	public void getByIdException() throws RecordNotFoundException {
		employee.setId(55);
		Optional<Employee> empOptional = Optional.ofNullable(employee);
		when(employeeRepository.findById(employee.getId())).thenThrow(RuntimeException.class);
		Employee byId = employeeService.getById(55);
		when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
		assertEquals(byId.getId(), 409);
	}

	@Test
	public void getAll() {
		PaginationResponse res = new PaginationResponse();
		PageRequest pageRequest = PageRequest.of(0, 4, Sort.by(Order.asc("id")));
		res.setPageNo(1);
		res.setPageSize(4);
		res.setTotalRecord(5L);
		when(PageRequest.of(res.getPageNo(), res.getPageSize(), Sort.by(Order.asc("id")))).thenReturn(pageRequest);
		PaginationResponse all = employeeService.getAll(res);
		assertNotNull(all);
	}
}
