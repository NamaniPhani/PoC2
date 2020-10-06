package com.poc5.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.poc5.dao.EmployeeDao;
import com.poc5.model.Employee;
import com.poc5.response.EmployeeResponse;
import com.poc5.service.EmployeeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

	@InjectMocks
	private EmployeeServiceImpl employeeServiceImpl;

	@Mock
	private EmployeeDao employeeDao;

	@Spy
	private EmployeeResponse response;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
		MockMvcBuilders.standaloneSetup(employeeServiceImpl).build();
	}

	public List<Employee> getEmployee() {
		List<Employee> employees = new ArrayList<>();
		Employee employee = new Employee();
		employee.setId(16);
		employee.setEmpName("emp-01");
		employee.setSalary(20000);
		employee.setCity("HYD");
		employee.setPhoneNumber(1234);
		employee.setStatus("0");
		employees.add(employee);
		return employees;
	}

	public List<Employee> getEmployee_negative() {
		List<Employee> employees = new ArrayList<>();
		Employee employee = new Employee();
		employee.setId(16);
		employee.setEmpName("emp-01");
		employee.setSalary(20000);
		employee.setCity("HYD");
		employee.setPhoneNumber(0);
		employee.setStatus("0");
		employees.add(employee);
		return employees;
	}

	@Test
	public void createEmployeeTest() {
		when(employeeDao.save(getEmployee().get(0))).thenReturn(getEmployee().get(0));
		EmployeeResponse createEmployee = employeeServiceImpl.createEmployee(getEmployee().get(0));
		assertEquals(createEmployee.getStatusCode(), "200");
	}

	@Test
	public void createEmployeeTest_negative() {
		when(employeeDao.save(getEmployee().get(0))).thenThrow(RuntimeException.class);
		EmployeeResponse createEmployee = employeeServiceImpl.createEmployee(getEmployee().get(0));
		assertEquals(createEmployee.getStatusCode(), "422");
	}

	@Test
	public void getEmployeeByIdTest() {
		when(employeeDao.readEmployee(16)).thenReturn(getEmployee().get(0));
		EmployeeResponse employeeById = employeeServiceImpl.getEmployeeById(16);
		assertEquals(employeeById.getStatusCode(), "200");
	}

	@Test
	public void getEmployeeByIdTest_exception() {
		when(employeeDao.readEmployee(Mockito.anyInt())).thenThrow(RuntimeException.class);
		EmployeeResponse employeeById = employeeServiceImpl.getEmployeeById(Mockito.anyInt());
		assertEquals(employeeById.getStatusCode(), "422");
	}

	@Test
	public void getEmployeeByIdTest_negative() {
		when(employeeDao.readEmployee(165)).thenReturn(null);
		EmployeeResponse employeeById = employeeServiceImpl.getEmployeeById(165);
		assertEquals(employeeById.getStatusCode(), "200");
	}

	@Test
	public void updateEmployeeTest() {
		when(employeeDao.save(getEmployee().get(0))).thenReturn(getEmployee().get(0));
		EmployeeResponse updateEmployee = employeeServiceImpl.updateEmployee(getEmployee().get(0));
		assertEquals(updateEmployee.getStatusCode(), "200");
	}

	@Test
	public void updateEmployeeTest_exception() {
		when(employeeDao.save(getEmployee().get(0))).thenThrow(RuntimeException.class);
		EmployeeResponse updateEmployee = employeeServiceImpl.updateEmployee(getEmployee().get(0));
		assertEquals(updateEmployee.getStatusCode(), "422");
	}

	@Test
	public void deleteEmployeeTest() {
		when(employeeDao.deleteEmployee(17)).thenReturn(1);
		EmployeeResponse deleteEmployee = employeeServiceImpl.deleteEmployee(17);
		assertEquals(deleteEmployee.getStatusCode(), "200");
	}

	@Test
	public void deleteEmployeeTest_negative() {
		when(employeeDao.deleteEmployee(17)).thenReturn(0);
		EmployeeResponse deleteEmployee = employeeServiceImpl.deleteEmployee(0);
		assertEquals(deleteEmployee.getStatusCode(), "200");
	}

	@Test
	public void deleteEmployeeTest_exception() {
		when(employeeDao.deleteEmployee(Mockito.anyInt())).thenThrow(RuntimeException.class);
		EmployeeResponse deleteEmployee = employeeServiceImpl.deleteEmployee(Mockito.anyInt());
		assertEquals(deleteEmployee.getStatusCode(), "422");
	}

}
