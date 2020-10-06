package com.ojas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.ojas.controller.EmployeeController;
import com.ojas.exception.RecordNotFoundException;
import com.ojas.model.Employee;
import com.ojas.response.EmployeeResponseData;
import com.ojas.service.EmployeeService;

import net.bytebuddy.implementation.bytecode.Throw;

public class EmployeeControllerTests {

	@InjectMocks
	private EmployeeController employeeController;

	@Mock
	private EmployeeService employeeService;

	/*
	 * @Autowired private MockMvc mockMvc;
	 */

	@BeforeEach
	public void init() {

		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void createEmployee_ok() throws SQLException {

		Employee employee = new Employee();
		employee.setName("santhosh");
		employee.setCity("hyderabad");
		employee.setPhone(8096736);
		employee.setSalary(20000d);

		when(employeeService.createEmployee(employee)).thenReturn(employee);

		ResponseEntity<?> createEmployee = employeeController.createEmployee(employee);

		assertEquals(createEmployee.getStatusCode(), HttpStatus.OK);

	}

	@Test
	public void createEmployee_ok_notcreated() throws SQLException {

		Employee employee = new Employee();
		employee.setName("santhosh");
		employee.setCity("hyderabad");
		employee.setPhone(8096736);
		employee.setSalary(20000d);

		when(employeeService.createEmployee(employee)).thenReturn(null);

		ResponseEntity<?> createEmployee = employeeController.createEmployee(employee);

		assertEquals(createEmployee.getStatusCode(), HttpStatus.CONFLICT);

	}

	@Test
	public void createEmployee_validations() throws Exception {

		Employee employee = null;
	

		ResponseEntity<?> createEmployee = employeeController.createEmployee(employee);

		assertEquals(createEmployee.getStatusCode(), HttpStatus.BAD_REQUEST);

	}

	@Test
	public void createEmployee_validationOfFeilds() throws Exception {
		Employee employee = new Employee();
		employee.setName("");
		employee.setCity("");
		employee.setPhone(null);
		employee.setSalary(null);

		ResponseEntity<?> createEmployee = employeeController.createEmployee(employee);

		assertEquals(createEmployee.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);

	}

	@Test
	public void createEmployee_sqlException() throws SQLException {

		Employee employee = new Employee();
		employee.setName("santhosh");
		employee.setCity("hyderabad");
		employee.setPhone(8096736);
		employee.setSalary(20000d);

		when(employeeService.createEmployee(employee)).thenThrow(SQLException.class);

		ResponseEntity<?> createEmployee = employeeController.createEmployee(employee);

		assertEquals(createEmployee.getStatusCode(), HttpStatus.CONFLICT);

	}

	@Test
	public void createEmployee_Exception() throws SQLException {

		Employee employee = new Employee();
		employee.setName("santhosh");
		employee.setCity("hyderabad");
		employee.setPhone(80967365);
		employee.setSalary(20000d);

		when(employeeService.createEmployee(employee)).thenThrow(new RuntimeException());

		ResponseEntity<?> createEmployee = employeeController.createEmployee(employee);

		assertEquals(createEmployee.getStatusCode(), HttpStatus.CONFLICT);

	}

	@Test
	public void getEmployeeByid_ok() throws SQLException, RecordNotFoundException {

		Employee employee = new Employee();
		employee.setId(2);
		employee.setName("santhosh");
		employee.setCity("hyderabad");
		employee.setPhone(809673);
		employee.setSalary(20000d);

		when(employeeService.getEmployeeById(2)).thenReturn(employee);

		ResponseEntity<?> getEmployee = employeeController.getEmployeeById(2);

		assertEquals(getEmployee.getStatusCode(), HttpStatus.OK);

	}

	@Test
	public void getEmployeeByid_id_null_check() {

		ResponseEntity<?> getEmployee = employeeController.getEmployeeById(null);

		assertEquals(getEmployee.getStatusCode(), HttpStatus.BAD_REQUEST);

	}

	@Test
	public void getEmployeeByid_recordnotfoundexception_check() throws SQLException, RecordNotFoundException {

		when(employeeService.getEmployeeById(2)).thenThrow(RecordNotFoundException.class);

		ResponseEntity<?> getEmployee = employeeController.getEmployeeById(2);

		assertEquals(getEmployee.getStatusCode(), HttpStatus.CONFLICT);

	}

	@Test
	public void getEmployeeByid_exception_check() throws SQLException, RecordNotFoundException {

		when(employeeService.getEmployeeById(2)).thenThrow(RuntimeException.class);

		ResponseEntity<?> getEmployee = employeeController.getEmployeeById(2);

		assertEquals(getEmployee.getStatusCode(), HttpStatus.CONFLICT);

	}

	@Test
	public void updateEmployee_ok() throws SQLException, RecordNotFoundException {

		Employee employee = new Employee();
		employee.setId(2);
		employee.setName("santhosh");
		employee.setCity("hyderabad");
		employee.setPhone(809673);
		employee.setSalary(20000d);

		when(employeeService.updateEmployee(employee)).thenReturn(employee);

		ResponseEntity<?> updateEmployee = employeeController.updateEmployee(employee);

		assertEquals(updateEmployee.getStatusCode(), HttpStatus.OK);

	}

	@Test
	public void updateEmployee_ok_notcreated() throws SQLException, RecordNotFoundException {

		Employee employee = new Employee();
		employee.setId(2);
		employee.setName("santhosh");
		employee.setCity("hyderabad");
		employee.setPhone(8096736);
		employee.setSalary(20000d);

		when(employeeService.updateEmployee(employee)).thenReturn(null);

		ResponseEntity<?> updateEmployee = employeeController.updateEmployee(employee);

		assertEquals(updateEmployee.getStatusCode(), HttpStatus.CONFLICT);

	}

	@Test
	public void updateEmployee_validations() throws Exception {

		Employee employee = null;

		ResponseEntity<?> updateEmployee = employeeController.updateEmployee(employee);

		assertEquals(updateEmployee.getStatusCode(), HttpStatus.BAD_REQUEST);

	}

	@Test
	public void updateEmployee_validationOfFeilds() throws Exception {
		Employee employee = new Employee();
		employee.setName("");
		employee.setId(10);
		employee.setCity("hyderabad");
		employee.setPhone(8096736);
		employee.setSalary(20000d);

		ResponseEntity<?> updateEmployee = employeeController.updateEmployee(employee);

		assertEquals(updateEmployee.getStatusCode(), HttpStatus.UNPROCESSABLE_ENTITY);

	}

	@Test
	public void updateEmployee_sqlException() throws SQLException, RecordNotFoundException {

		Employee employee = new Employee();
		employee.setId(2);
		employee.setName("santhosh");
		employee.setCity("hyderabad");
		employee.setPhone(8096736);
		employee.setSalary(20000d);

		when(employeeService.updateEmployee(employee)).thenThrow(SQLException.class);

		ResponseEntity<?> updateEmployee = employeeController.updateEmployee(employee);

		assertEquals(updateEmployee.getStatusCode(), HttpStatus.CONFLICT);

	}

	@Test
	public void updateEmployee_recordnotfoundexception_check() throws SQLException, RecordNotFoundException {

		Employee employee = new Employee();
		employee.setId(2);
		employee.setName("santhosh");
		employee.setCity("hyderabad");
		employee.setPhone(8096736);
		employee.setSalary(20000d);
		when(employeeService.updateEmployee(employee)).thenThrow(RecordNotFoundException.class);

		ResponseEntity<?> updateEmployee = employeeController.updateEmployee(employee);

		assertEquals(updateEmployee.getStatusCode(), HttpStatus.CONFLICT);

	}

	@Test
	public void updateEmployee_Exception() throws SQLException, RecordNotFoundException {

		Employee employee = new Employee();
		employee.setId(2);
		employee.setName("santhosh");
		employee.setCity("hyderabad");
		employee.setPhone(8096736);
		employee.setSalary(20000d);

		when(employeeService.updateEmployee(employee)).thenThrow(new RuntimeException());

		ResponseEntity<?> updateEmployee = employeeController.updateEmployee(employee);

		assertEquals(updateEmployee.getStatusCode(), HttpStatus.CONFLICT);

	}

	@Test
	public void deleteEmployeeByid_ok() throws SQLException, RecordNotFoundException {

		ResponseEntity<?> deleteEmployee = employeeController.deleteEmployeeById(2);

		assertEquals(deleteEmployee.getStatusCode(), HttpStatus.OK);

	}

	@Test
	public void deleteEmployeeByid_id_null_check() {

		ResponseEntity<?> deleteEmployeeById = employeeController.deleteEmployeeById(null);

		assertEquals(deleteEmployeeById.getStatusCode(), HttpStatus.BAD_REQUEST);

	}

	@Test
	public void deleteEmployeeByid_recordnotfoundexception_check() throws SQLException, RecordNotFoundException {

		when(employeeService.deleteEmployeeById(2)).thenThrow(RecordNotFoundException.class);

		ResponseEntity<?> deleteEmployeeById = employeeController.deleteEmployeeById(2);

		assertEquals(deleteEmployeeById.getStatusCode(), HttpStatus.CONFLICT);

	}

	@Test
	public void deleteEmployeeByid_sqlexception_check() throws SQLException, RecordNotFoundException {

		when(employeeService.deleteEmployeeById(2)).thenThrow(SQLException.class);

		ResponseEntity<?> deleteEmployeeById = employeeController.deleteEmployeeById(2);

		assertEquals(deleteEmployeeById.getStatusCode(), HttpStatus.CONFLICT);

	}

	@Test
	public void deleteEmployeeByid_exception_check() throws SQLException, RecordNotFoundException {

		when(employeeService.deleteEmployeeById(2)).thenThrow(RuntimeException.class);

		ResponseEntity<?> deleteEmployeeById = employeeController.deleteEmployeeById(2);

		assertEquals(deleteEmployeeById.getStatusCode(), HttpStatus.CONFLICT);

	}

	@Test
	public void getAllEmployeeInfo_No_search() {

		EmployeeResponseData employeeResponseData = new EmployeeResponseData();

		employeeResponseData.setNoOfrecords(3l);
		employeeResponseData.setTotalNumberOfPages(1);
		List<Employee> arrayList = new ArrayList<>();
		arrayList.add(new Employee(1, "pranay", 2500d, "hyderabad", 78387));
		arrayList.add(new Employee(1, "mani", 3500d, "hyderabad", 74387));
		arrayList.add(new Employee(1, "santhosh", 2500d, "Sydney", 73387));
		employeeResponseData.setList(arrayList);
		when(employeeService.getAllEmployeeDetails(1, 3)).thenReturn(employeeResponseData);

		ResponseEntity<?> allEmployeeInfo = employeeController.getAllEmployeeInfo("null", 1, 3);

		assertEquals(allEmployeeInfo.getStatusCode(), HttpStatus.OK);

	}

	@Test
	public void getAllEmployeeInfo_Search() {

		EmployeeResponseData employeeResponseData = new EmployeeResponseData();

		employeeResponseData.setNoOfrecords(3l);
		employeeResponseData.setTotalNumberOfPages(1);
		List<Employee> arrayList = new ArrayList<>();
		arrayList.add(new Employee(1, "pranay", 2500d, "hyderabad", 78387));
		arrayList.add(new Employee(1, "pranay", 3500d, "hyderabad", 74387));
		employeeResponseData.setList(arrayList);
		
		
		
		when(employeeService.getAllEmployeeDetails("pranay", 1, 3)).thenReturn(employeeResponseData);

		ResponseEntity<?> allEmployeeInfo = employeeController.getAllEmployeeInfo("pranay", 1, 3);

		assertEquals(allEmployeeInfo.getStatusCode(), HttpStatus.OK);

	}

	@Test
	public void getAllEmployeeInfo_null_check() {

		ResponseEntity<?> allEmployeeInfo = employeeController.getAllEmployeeInfo("undefined", null, 3);

		assertEquals(allEmployeeInfo.getStatusCode(), HttpStatus.BAD_REQUEST);

	}

	@Test
	public void getAllEmployeeInfo_Exception() {

		when(employeeService.getAllEmployeeDetails("pranay", 1, 3)).thenThrow(RuntimeException.class);

		ResponseEntity<?> allEmployeeInfo = employeeController.getAllEmployeeInfo("pranay", 1, 3);

		assertEquals(allEmployeeInfo.getStatusCode(), HttpStatus.CONFLICT);

	}

}
