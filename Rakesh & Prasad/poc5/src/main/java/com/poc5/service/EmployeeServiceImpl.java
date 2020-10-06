package com.poc5.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.poc5.dao.EmployeeDao;
import com.poc5.model.Employee;
import com.poc5.response.EmployeeResponse;
import com.poc5.response.PaginationResponse;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDao empdao;
	EmployeeResponse employeeResponse = new EmployeeResponse();

	@Override
	public EmployeeResponse createEmployee(Employee emp) {
		try {

			List<Employee> emplist = new ArrayList<>();
			Employee saveEmp = empdao.save(emp);
			emplist.add(saveEmp);
			employeeResponse.setStatusCode("200");
			employeeResponse.setStatusMessage("Employee inserted Successfully");
			employeeResponse.setEmplist(emplist);
			return employeeResponse;

		} catch (Exception e) {
			employeeResponse.setStatusCode("422");
			employeeResponse.setStatusMessage(e.getMessage());
			return employeeResponse;
		}
	}

	@Override
	public EmployeeResponse getEmployeeById(int id) {
		try {
			Employee readEmployee = empdao.readEmployee(id);
			List<Employee> emplist = new ArrayList<>();

			if (readEmployee != null) {
				emplist.add(readEmployee);
				employeeResponse.setStatusCode("200");
				employeeResponse.setStatusMessage("Employee retrieved successfully");
				employeeResponse.setEmplist(emplist);
				return employeeResponse;
			} else {
				employeeResponse.setStatusCode("200");
				employeeResponse.setStatusMessage("no records found");
				employeeResponse.setEmplist(emplist);
				return employeeResponse;
			}
		} catch (Exception e) {
			employeeResponse.setStatusCode("422");
			employeeResponse.setStatusMessage(e.getMessage());
			return employeeResponse;
		}
	}

	@Override
	public EmployeeResponse updateEmployee(Employee emp) {
		try {
			Employee empl = new Employee();
			empl.setId(emp.getId());
			empl.setEmpName(emp.getEmpName());
			empl.setPhoneNumber(emp.getPhoneNumber());
			empl.setCity(emp.getCity());
			empl.setSalary(emp.getSalary());
			Employee save = empdao.save(empl);
			List<Employee> emplist = new ArrayList<>();
			emplist.add(save);
			employeeResponse.setStatusCode("200");
			employeeResponse.setStatusMessage("Employee updated successfully");
			employeeResponse.setEmplist(emplist);
			return employeeResponse;
		} catch (Exception e) {
			employeeResponse.setStatusCode("422");
			employeeResponse.setStatusMessage(e.getMessage());
			return employeeResponse;
		}
	}

	@Override
	public EmployeeResponse deleteEmployee(int id) {
		try {
			int deleteEmployee = empdao.deleteEmployee(id);
			List<Employee> emplist = new ArrayList<>();
			if (deleteEmployee > 0) {
				employeeResponse.setStatusCode("200");
				employeeResponse.setStatusMessage("Employee deleted successfully");
				return employeeResponse;
			} else {
				employeeResponse.setStatusCode("200");
				employeeResponse.setStatusMessage("No record found to delete");
				employeeResponse.setEmplist(emplist);
				return employeeResponse;
			}
		} catch (Exception e) {
			employeeResponse.setStatusCode("422");
			employeeResponse.setStatusMessage(e.getMessage());
			return employeeResponse;
		}
	}

	@Override
	public Object getAllEmployees(int pageNo, int pageSize) {
		PaginationResponse paginationres = new PaginationResponse();
		PageRequest pagination = PageRequest.of(pageNo, pageSize, Sort.by(Order.desc("id")));
		Page<Employee> findAll = empdao.findAll(pagination);
		if (!findAll.isEmpty() && findAll != null) {
			paginationres.setObj(findAll.getContent());
			paginationres.setPageNo(findAll.getNumber());
			paginationres.setTotalRecord(findAll.getNumberOfElements());
			paginationres.setPageSize(findAll.getSize());
			return paginationres;
		} else {
			employeeResponse.setStatusCode("200");
			employeeResponse.setStatusMessage("No records found");
			return employeeResponse;
		}
	}
}
