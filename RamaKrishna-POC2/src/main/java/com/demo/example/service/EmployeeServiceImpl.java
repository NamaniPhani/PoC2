package com.demo.example.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.example.dao.EmployeeDAO;
import com.demo.example.entity.Employee;
import com.demo.example.response.Response;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDao;

	Response response = new Response();
	Logger logger = Logger.getLogger(this.getClass());

	@Override
	public ResponseEntity<Object> createEmployee(Employee employee) {
		logger.debug("incoming request for createEmployee EmployeeServiceImpl ::::" + employee);

		if (employee != null) {
			List<Employee> listofEmployees = new ArrayList<Employee>();
			Employee saveJob = employeeDao.createEmployee(employee);
			logger.debug("response for createEmployee EmployeeServiceImpl ::::" + saveJob);
			listofEmployees.add(saveJob);
			response.setTimestamp(LocalDateTime.now());
			response.setStatusCode("200");
			response.setStatusMessage("Employee created successfully");
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {
			response.setTimestamp(LocalDateTime.now());
			response.setStatusCode("422");
			response.setStatusMessage("failed to create Employee");
			return new ResponseEntity<Object>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@Override
	public ResponseEntity<Object> findEmployeeById(int employeeId) {
		logger.debug("incoming request for getJobById EmployeeServiceImpl ::::" + employeeId);
		List<Employee> listofEmployees1 = new ArrayList<>();
		if (employeeId > 0) {
			Employee listofEmployees = employeeDao.findEmployeeById(employeeId);
			logger.debug("response for EmployeeJobById EmployeeServiceImpl ::::" + employeeId);
			listofEmployees1.add(listofEmployees);
			response.setTimestamp(LocalDateTime.now());
			response.setStatusCode("200");
			response.setStatusMessage("record fetched successfully");
			response.setListofEmployees(listofEmployees1);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {
			response.setTimestamp(LocalDateTime.now());
			response.setStatusCode("422");
			response.setStatusMessage("record is not found");
			return new ResponseEntity<Object>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResponseEntity<Object> findAllEmployees() {
		List<Employee> listofEmployees = employeeDao.findAllEmployees();
		logger.debug("response for findAllEmployees EmployeeServiceImpl ::::" + listofEmployees);
		if (!listofEmployees.isEmpty()) {
			response.setTimestamp(LocalDateTime.now());
			response.setStatusCode("200"); 
			response.setStatusMessage("records fetched successfully");
			response.setListofEmployees(listofEmployees);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {
			response.setTimestamp(LocalDateTime.now());
			response.setStatusCode("422");
			response.setStatusMessage("records is not found");
			return new ResponseEntity<Object>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@Override
	public ResponseEntity<Object> UpdateEmployee(Employee employee, int employeeId) {
		logger.debug("response for UpdateEmployees EmployeeServiceImpl ::::" + employee);
		if (employee != null) {
			employeeDao.UpdateEmployee(employee, employeeId);
			response.setTimestamp(LocalDateTime.now());
			response.setStatusCode("200");
			response.setStatusMessage("record updated successfully");
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {
			response.setTimestamp(LocalDateTime.now());
			response.setStatusCode("422");
			response.setStatusMessage("record cannot be updated");
			return new ResponseEntity<Object>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		}

	}

	@Override
	public ResponseEntity<Object> deleteEmployeeById(int employeeId) {
		logger.debug("response for deleteEmployees EmployeeServiceImpl ::::" + employeeId);
		employeeDao.deleteEmployeeById(employeeId);
		if (employeeId > 0) {
			response.setTimestamp(LocalDateTime.now());
			response.setStatusCode("200");
			response.setStatusMessage("record deleted successfully");
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {
			response.setTimestamp(LocalDateTime.now());
			response.setStatusCode("422");
			response.setStatusMessage("record cannot be deleted");
			return new ResponseEntity<Object>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@Override
	public ResponseEntity<Object> findEmployeesByPagination(int pageNo, int pageSize) {
		ResponseEntity<Object> findAllEmployees = findAllEmployees();
		logger.debug("response for findEmployeesByPagination EmployeeServiceImpl ::::" + pageNo + "," + pageSize);
		List<Employee> findEmployeesByPagination = employeeDao
				.findEmployeesByPagination( response.getListofEmployees(), pageNo, pageSize);
		if (!findEmployeesByPagination.isEmpty()) {
			response.setTimestamp(LocalDateTime.now());
			response.setStatusCode("200");
			response.setStatusMessage("Pagination Successfully Implemented");
			response.setListofEmployees(findEmployeesByPagination);
			return new ResponseEntity<Object>(response, HttpStatus.OK);
		} else {
			logger.error("error in findEmployeesByPagination EmployeeServiceImpl ::::");
			response.setTimestamp(LocalDateTime.now());
			response.setStatusCode("422");
			response.setStatusMessage("Error in Employee pagination");
			return new ResponseEntity<Object>(response, HttpStatus.UNPROCESSABLE_ENTITY);

		}
	}

}
