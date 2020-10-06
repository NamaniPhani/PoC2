package com.ojas.poc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.ojas.poc.exception.PaginationResponse;
import com.ojas.poc.exception.RecordNotFoundException;
import com.ojas.poc.model.Employee;
import com.ojas.poc.repository.EmployeeRepository;
import com.ojas.poc.response.Response;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Response saveEmployee(Employee employee) {
		try {
			Employee save = employeeRepository.save(employee);
			if (save == null)
				return new Response(null, "Failed to create Employee data ", 409);
			else
				return new Response(save, "Data is created Successfully", 200);
		} catch (Exception e) {
			return new Response(null, "Please fill the employee details", 409);
		}
	}

	public Employee getById(Integer id) throws RecordNotFoundException {
		Optional<Employee> findById = employeeRepository.findById(id);
		if (findById.isPresent()) {
			return findById.get();
		} else {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
	}

	public Employee updateEmployee(Employee employee) {
		Optional<Employee> findById = employeeRepository.findById(employee.getId());
		Employee newEmployee = findById.get();
		newEmployee.setName(employee.getName());
		newEmployee.setCity(employee.getCity());
		newEmployee.setPhone(employee.getPhone());
		newEmployee.setSal(employee.getSal());
		newEmployee = employeeRepository.save(newEmployee);
		return newEmployee;
	}

	public void deleteEmployee(Integer id) throws RecordNotFoundException {
		Optional<Employee> findById = employeeRepository.findById(id);
		if (findById.isPresent()) {
			employeeRepository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No employee record exist for given id");
		}
	}

	public PaginationResponse getAll(PaginationResponse paginationres) {
		PaginationResponse res = new PaginationResponse();
		PageRequest pagination = PageRequest.of(paginationres.getPageNo(), paginationres.getPageSize(),
				Sort.by(Order.asc("id")));
		Page<Employee> findAll = employeeRepository.findAll(pagination);
		res.setEmp(findAll.getContent());
		res.setPageNo(findAll.getNumber());
		res.setTotalRecord(findAll.getTotalElements());
		res.setPageSize(findAll.getSize());
		return res;
	}

	public List<Employee> findByName(String name) {
		List<Employee> findByName = employeeRepository.findByName(name);
		return findByName;
	}
}
