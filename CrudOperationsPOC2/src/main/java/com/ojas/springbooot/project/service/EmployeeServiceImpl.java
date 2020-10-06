package com.ojas.springbooot.project.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ojas.springbooot.project.dao.EmployeRepository;
import com.ojas.springbooot.project.exception.CustomException;
import com.ojas.springbooot.project.model.Employee;
import com.ojas.springbooot.project.util.Response;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeRepository employeRepository;

	Logger log = Logger.getLogger(this.getClass());

	@Override
	public ResponseEntity<Object> save(Employee emp) {
		log.debug("Saving redords...");
		if (emp != null) {
			Employee save = employeRepository.save(emp);
			return new ResponseEntity<Object>(save, HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(new CustomException(409, "Invalid data"), HttpStatus.CONFLICT);

		}
	}

	/*
	 * @Override public ResponseEntity<Object> getAllEmployees() {
	 * log.debug("get all records.."); List<Employee> findAll =
	 * employeRepository.findAll(); if (findAll != null) { return new
	 * ResponseEntity<Object>(findAll, HttpStatus.OK); } return new
	 * ResponseEntity<Object>(new CustomException(409, "Invalid list data"),
	 * HttpStatus.CONFLICT);
	 * 
	 * }
	 */
	@Override
	public ResponseEntity<Object> delete(int id) {
		log.debug("delete records by id..");
		Response res = new Response();
		if (id > 0) {
			employeRepository.deleteById(id);
			res.setMsg("Record has deleted successfully");
			return new ResponseEntity<Object>(res, HttpStatus.OK);
		}
		res.setMsg("Id is lessthan zero");
		return new ResponseEntity<Object>(res, HttpStatus.CONFLICT);

	}

	@Override
	public ResponseEntity<Object> getByEmpId(int id) {
		log.debug("find records by id..");
		Optional<Employee> employee = employeRepository.findById(id);
		if (employee != null) {
			return new ResponseEntity<Object>(employee, HttpStatus.OK);
		}
		return new ResponseEntity<Object>(new CustomException(409, "Invalid data"), HttpStatus.CONFLICT);

	}

	@Override
	public ResponseEntity<Object> getAllEmployees(int pageNo, int pageSize) throws Exception {
		log.debug("Incoming request employee service getall method ");
		Pageable pageRequest = PageRequest.of(pageNo, pageSize);
		List<Employee> findAll = employeRepository.findAll(pageRequest).toList();
		if (findAll.isEmpty()) {
			log.error("Record Not found");
			return new ResponseEntity<>(new CustomException(409, "Record Not found"), HttpStatus.CONFLICT);
		}
		Response response = new Response();
		response.setStatusCode("SucessStatus");
		response.setStatus("Sucess");
		response.setMsg("GetEmployeeMsg");
		response.setData(findAll);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
