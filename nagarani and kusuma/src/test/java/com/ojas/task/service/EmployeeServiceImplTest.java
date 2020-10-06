package com.ojas.task.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.ojas.task.model.Employee;
import com.ojas.task.model.Response;
import com.ojas.task.repository.EmployeeRepo;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {

	Logger log = Logger.getLogger(this.getClass());

	@InjectMocks
	EmployeeServiceImpl service;

	@Mock
	EmployeeRepo repository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this.getClass());
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
	public void testSaveOk() {
		Employee emp = getEmployee();
		when(repository.save(emp)).thenReturn(emp);
		Response response = service.save(emp);
		System.out.println(response.getMsg() + "      " + response.getObject());
		assertEquals("200", response.getStatus());
	}

	/*
	 * @Test(expected = Exception.class) public void testSaveConflict() { Employee
	 * emp = getEmployee(); when(repository.save(emp)).thenThrow(Exception.class);
	 * Response response = service.save(emp); assertEquals("409",
	 * response.getStatus()); }
	 */
	@Test
	public void testFindById() {
		Employee emp = getEmployee();
		when(repository.findById(1)).thenReturn(Optional.of(emp));
		Response response = service.findById(1);
		assertEquals("200", response.getStatus());
	}

	@Test
	public void testFindByIdEmpty() {
		when(repository.findById(1)).thenReturn(Optional.empty());
		Response response = service.findById(1);
		assertEquals("400", response.getStatus());
	}

	@Test
	public void testFindAllPage() {
		List<Employee> listEmp = new ArrayList<Employee>();
		listEmp.add(getEmployee());
		Page<Employee> pageEmp = new PageImpl<>(listEmp);
		when(repository.findAll(org.mockito.Matchers.isA(Pageable.class))).thenReturn(pageEmp);
		Response response = service.findAll(0, 5);
		assertEquals("200", response.getStatus());
	}

	@Test
	public void testFindAllPageEmpty() {
		List<Employee> listEmp = new ArrayList<Employee>();
		listEmp.add(getEmployee());
		Page<Employee> tasks = Mockito.mock(Page.class);
		when(repository.findAll(org.mockito.Matchers.isA(Pageable.class))).thenReturn(tasks);
		Response response = service.findAll(0, 5);
		assertEquals("400", response.getStatus());
	}

	@Test
	public void testFindAll() {
		List<Employee> listEmp = new ArrayList<Employee>();
		listEmp.add(getEmployee());
		when(repository.findAll()).thenReturn(listEmp);
		Response response = service.findAll();
		assertEquals("200", response.getStatus());
	}

	@Test 
	public void testFindAllEmpty() {
		List<Employee> listEmp = new ArrayList<Employee>();
		when(repository.findAll()).thenReturn(listEmp); 
		Response response = service.findAll();
		assertEquals("400", response.getStatus());
	}

	@Test
	public void testDelete() {
		Response response = service.delete(1);
		assertEquals("200", response.getStatus());
	}

}
