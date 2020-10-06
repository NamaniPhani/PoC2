package com.ojas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.ojas.exception.RecordNotFoundException;
import com.ojas.model.Employee;
import com.ojas.repository.EmployeeRepository;
import com.ojas.response.EmployeeResponseData;
import com.ojas.service.EmployeeService;

public class EmployeeServiceTests {

	@InjectMocks
	private EmployeeService employeeService;

	@Mock
	private EmployeeRepository employeeRepository;

	/*
	 * @Autowired private MockMvc mockMvc;
	 */

	@BeforeEach
	public void init() {

		MockitoAnnotations.initMocks(this);

	}

	@Test
	public void createEmployee() throws SQLException {
		Employee employee = new Employee();
		employee.setName("pranay");
		employee.setCity("hyderabad");
		employee.setPhone(9949467);
		employee.setSalary(10000d);

		when(employeeRepository.save(employee)).thenReturn(employee);

		Employee createEmployee = employeeService.createEmployee(employee);

		assertEquals(createEmployee, employee);

	}

	@Test
	public void getEmployee() throws SQLException, RecordNotFoundException {
		Employee employee = new Employee();
		employee.setId(10);
		employee.setName("pranay");
		employee.setCity("hyderabad");
		employee.setPhone(9949467);
		employee.setSalary(10000d);

		when(employeeRepository.findById(10)).thenReturn(Optional.ofNullable(employee));

		Employee getEmployee = employeeService.getEmployeeById(10);

		assertEquals(getEmployee, employee);

	}

	@Test
	public void getEmployee_throwRecordNotFoundException() throws SQLException, RecordNotFoundException {

		when(employeeRepository.findById(10)).thenReturn(Optional.ofNullable(null));

		assertThrows(RecordNotFoundException.class, () -> employeeService.getEmployeeById(10));

	}

	@Test
	public void updateEmployee_NoRecordFoundException() throws SQLException, RecordNotFoundException {

		Employee employee1 = new Employee();
		employee1.setId(10);
		employee1.setName("naveen");
		employee1.setCity("hyderabad");
		employee1.setPhone(9949467);
		employee1.setSalary(10000d);

		// OngoingStubbing<Optional<Employee>> thenReturn =
		// when(employeeRepository.findById(10)).thenReturn(Optional.ofNullable(employee));

		when(employeeRepository.findById(10)).thenReturn(Optional.ofNullable(null));

		assertThrows(RecordNotFoundException.class, () -> employeeService.updateEmployee(employee1));

		// when(employeeRepository.findById(10)).thenReturn(Optional.ofNullable(employee));

	}

	@Test
	public void updateEmployee() throws SQLException, RecordNotFoundException {

		Employee employee = new Employee();
		employee.setId(10);
		employee.setName("naveen");
		employee.setCity("hyderabad");
		employee.setPhone(9949467);
		employee.setSalary(10000d);

		Optional<Employee> of = Optional.of(employee);

		when(employeeRepository.findById(10)).thenReturn(of);

		when(employeeRepository.save(employee)).thenReturn(employee);

		Employee updateEmployee = employeeService.updateEmployee(employee);

		assertEquals(updateEmployee, employee);

	}

	@Test
	public void deleteEmployee() throws SQLException, RecordNotFoundException {
		Employee employee = new Employee();
		employee.setId(10);
		employee.setName("pranay");
		employee.setCity("hyderabad");
		employee.setPhone(9949467);
		employee.setSalary(10000d);

		when(employeeRepository.findById(10)).thenReturn(Optional.ofNullable(employee));

		boolean deleteEmployeeById = employeeService.deleteEmployeeById(10);

		assertEquals(deleteEmployeeById, true);

	}

	@Test
	public void deleteEmployee_throwRecordNotFoundException() throws SQLException, RecordNotFoundException {

		when(employeeRepository.findById(10)).thenReturn(Optional.ofNullable(null));

		assertThrows(RecordNotFoundException.class, () -> employeeService.deleteEmployeeById(10));

	}

	@Test
	public void getAllEmployeeDetails_PageNumber_PageSize() {

		EmployeeResponseData employeeResponseData = new EmployeeResponseData();

		employeeResponseData.setNoOfrecords(2l);
		employeeResponseData.setTotalNumberOfPages(1);
		List<Employee> arrayList = new ArrayList<>();
		arrayList.add(new Employee(1, "pranay", 2500d, "hyderabad", 78387));
		arrayList.add(new Employee(1, "pranay", 3500d, "hyderabad", 74387));
		employeeResponseData.setList(arrayList);

		Pageable paging = PageRequest.of(1, 3);

		Page<Employee> page = new PageImpl<>(arrayList);

		when(employeeRepository.findAll(paging)).thenReturn(page);

		EmployeeResponseData allEmployeeDetails = employeeService.getAllEmployeeDetails(1, 3);

		assertEquals(allEmployeeDetails.getList(), employeeResponseData.getList());

	}
	
	@Test
	public void getAllEmployeeDetails_PageNumber_PageSize_search() {

		EmployeeResponseData employeeResponseData = new EmployeeResponseData();

		employeeResponseData.setNoOfrecords(2l);
		employeeResponseData.setTotalNumberOfPages(1);
		List<Employee> arrayList = new ArrayList<>();
		arrayList.add(new Employee(1, "pranay", 2500d, "hyderabad", 78387));
		arrayList.add(new Employee(1, "pranay", 3500d, "hyderabad", 74387));
		employeeResponseData.setList(arrayList);

		Pageable paging = PageRequest.of(1, 3);

		Page<Employee> page = new PageImpl<>(arrayList);

		when(employeeRepository.findByNameContaining("pranay", paging)).thenReturn(page);

		EmployeeResponseData allEmployeeDetails = employeeService.getAllEmployeeDetails("pranay",1, 3);

		assertEquals(allEmployeeDetails.getList(), employeeResponseData.getList());

	}
	
	

}
