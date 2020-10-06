package com.demo.example.dao;

import java.util.List;

import com.demo.example.entity.Employee;

public interface EmployeeDAO {

	public Employee createEmployee(Employee contact);

	public Employee UpdateEmployee(Employee contact, int employeeId);

	public String deleteEmployeeById(int contactId);

	public Employee findEmployeeById(int contactId);

	public List<Employee> findAllEmployees();

	public List<Employee> findEmployeesByPagination(List<Employee> employeesList, int pageNo, int pageSize);

}
