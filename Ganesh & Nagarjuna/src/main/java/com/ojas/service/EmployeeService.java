package com.ojas.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ojas.exception.RecordNotFoundException;
import com.ojas.model.Employee;
import com.ojas.repository.EmployeeRepository;
import com.ojas.response.EmployeeResponseData;

@Service
public class EmployeeService {
	private static final Logger logger = LogManager.getLogger(EmployeeService.class);
	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee createEmployee(Employee employee) throws SQLException {

		logger.info("creating employee");

		Employee employee2 = employeeRepository.save(employee);

		return employee2;

	}

	public Employee getEmployeeById(Integer id) throws SQLException, RecordNotFoundException {
		logger.info("getting employee with id " + id);
		Optional<Employee> optional = employeeRepository.findById(id);

		if (optional.isPresent()) {

			return optional.get();
		} else {

			throw new RecordNotFoundException("No records found");

		}

	}

	public Employee updateEmployee(Employee employee) throws SQLException, RecordNotFoundException {

		logger.info("updating employee");

		Optional<Employee> optional = employeeRepository.findById(employee.getId());

		if (optional.isPresent()) {
			Employee employee2 = employeeRepository.save(employee);

			return employee2;

		} else {
			throw new RecordNotFoundException("no records find to update with employee id ");
		}

	}

	public boolean deleteEmployeeById(Integer id) throws RecordNotFoundException, SQLException {
		logger.info("deleting employee");

		Optional<Employee> optional = employeeRepository.findById(id);

		if (!optional.isPresent()) {

			throw new RecordNotFoundException("no records find to update with employee id ");
		}
		employeeRepository.delete(optional.get());
		return true;
	}
	
	public EmployeeResponseData getAllEmployeeDetails(int pageNo, int sizePerPage) {
		Pageable paging = PageRequest.of(pageNo, sizePerPage);

		Page<Employee> page = employeeRepository.findAll(paging);

		List<Employee> list = page.getContent();

		int totalPages = page.getTotalPages();

		long totalElements = page.getTotalElements();

		EmployeeResponseData response = new EmployeeResponseData();
		response.setNoOfrecords(totalElements); 
		response.setTotalNumberOfPages(totalPages);
		response.setList(list);
		return response;
	}
	
	public EmployeeResponseData getAllEmployeeDetails(String search, int pageNo, int sizePerPage) {

		Pageable paging = PageRequest.of(pageNo, sizePerPage);

		Page<Employee> page = employeeRepository.findByNameContaining(search, paging);

		int totalPages = page.getTotalPages();
		System.out.println(totalPages);
		long totalElements = page.getTotalElements();
		System.out.println(totalElements);
		List<Employee> list = page.getContent();

		EmployeeResponseData response = new EmployeeResponseData();
		response.setNoOfrecords(totalElements);
		response.setTotalNumberOfPages(totalPages);
		response.setList(list);
		return response;

	}


}
