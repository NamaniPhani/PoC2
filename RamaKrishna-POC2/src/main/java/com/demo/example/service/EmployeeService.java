package com.demo.example.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.demo.example.entity.Employee;
import com.demo.example.response.Response;

public interface EmployeeService {

	public ResponseEntity<Object> createEmployee(Employee employee);

	public ResponseEntity<Object> findEmployeeById(int employeeId);

	public ResponseEntity<Object>  findAllEmployees();

	public ResponseEntity<Object> UpdateEmployee(Employee employee, int employeeId);

	public ResponseEntity<Object> deleteEmployeeById(int employeeId);

	public ResponseEntity<Object> findEmployeesByPagination(int pageNo, int pageSize);

}
