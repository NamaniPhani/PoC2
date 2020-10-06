package com.ojas.service.impl;

import static com.ojas.util.Constants.EMPLOYEE_DELETE;
import static com.ojas.util.Constants.EMPLOYEE_SAVE;
import static com.ojas.util.Constants.EMPLOYEE_UPDATE;
import static com.ojas.util.Constants.GETBYID_DATA;
import static com.ojas.util.Constants.GET_EMPLOYEES_DATA;
import static com.ojas.util.Constants.INVALID_FIELDS;
import static com.ojas.util.Constants.INVALID_REQUEST;
import static com.ojas.util.Constants.RECORD_NOT_FOUND;
import static com.ojas.util.Constants.SUCCESS;
import static com.ojas.util.Constants.SUCCESS_STATUS;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ojas.exception.CustomException;
import com.ojas.model.Employee;
import com.ojas.repositories.EmployeeRepository;
import com.ojas.response.Response;
import com.ojas.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository emprepo;

	static Logger log = Logger.getLogger(EmployeeServiceImpl.class.getName());

	@Override
	public ResponseEntity<Object> saveEmployee(Employee employeerequest) throws Exception {
		log.debug("Incoming request employee service save method : " + employeerequest);
		if (employeerequest == null) { 
			log.error(INVALID_REQUEST);
			return new ResponseEntity<>(new CustomException(INVALID_REQUEST), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		if ((employeerequest.getName().isEmpty() || employeerequest.getName() == null)
				|| (employeerequest.getCity().isEmpty() || employeerequest.getCity() == null)
				|| employeerequest.getSalary() == null || employeerequest.getPhone() == null) {
			log.error(INVALID_FIELDS);
			return new ResponseEntity<>(new CustomException(INVALID_FIELDS), HttpStatus.UNPROCESSABLE_ENTITY);
		}

//		if (employeerequest.getId() == null || employeerequest.getId() == 0) {
		Employee save = emprepo.save(employeerequest);
		Response response = new Response();
		response.setStatusCode(SUCCESS_STATUS);
		response.setStatus(SUCCESS);
		response.setMsg(EMPLOYEE_SAVE);
		response.setData(save);
		return new ResponseEntity<>(response, HttpStatus.OK);
//		}

//		Employee findById = emprepo.findById(employeerequest.getId()).orElse(null);
//		findById.setName(employeerequest.getName());
//		findById.setPhone(employeerequest.getPhone());
//		findById.setSalary(employeerequest.getSalary());
//		findById.setCity(employeerequest.getCity());
//		Employee update = emprepo.save(findById);
//		Response response = new Response();
//		response.setStatusCode(SUCCESS_STATUS);
//		response.setStatus(SUCCESS);
//		response.setMsg(EMPLOYEE_UPDATE);
//		response.setData(update);
//		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> updateEmployee(Employee employeerequest) throws Exception {
		log.debug("Incoming request employee service update method : " + employeerequest);
		Integer id = employeerequest.getId();
		if (id == null) {
			log.error(INVALID_FIELDS);
			return new ResponseEntity<>(new CustomException(INVALID_FIELDS), HttpStatus.UNPROCESSABLE_ENTITY);
		}

		Employee findById = emprepo.findById(employeerequest.getId()).orElse(null);
		findById.setName(employeerequest.getName());
		findById.setPhone(employeerequest.getPhone());
		findById.setSalary(employeerequest.getSalary());
		findById.setCity(employeerequest.getCity());
		Employee update = emprepo.save(findById);
		Response response = new Response();
		response.setStatusCode(SUCCESS_STATUS);
		response.setStatus(SUCCESS); 
		response.setMsg(EMPLOYEE_UPDATE);
		response.setData(update);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> deleteEmployee(Integer id) throws Exception {
		log.debug("Incoming request employee service delete method : " + id);
		if (id == null) {
			log.error(RECORD_NOT_FOUND);
			return new ResponseEntity<>(new CustomException(INVALID_FIELDS), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		emprepo.deleteById(id);
		Response response = new Response();
		response.setStatusCode(SUCCESS_STATUS);
		response.setStatus(SUCCESS);
		response.setMsg(EMPLOYEE_DELETE);
		response.setData(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getByIdEmployee(Integer id) throws Exception {
		log.debug("Incoming request employee service getbyid method : " + id);
		Optional<Employee> findById = emprepo.findById(id);
		if (!findById.isPresent()) {
			log.error(RECORD_NOT_FOUND);
			return new ResponseEntity<>(new CustomException(RECORD_NOT_FOUND), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		Response response = new Response();
		response.setStatusCode(SUCCESS_STATUS);
		response.setStatus(SUCCESS);
		response.setMsg(GETBYID_DATA);
		response.setData(findById);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getAllEmployees(int pageNo, int pageSize) throws Exception {
		log.debug("Incoming request employee service getall method ");
		Pageable pageRequest = PageRequest.of(pageNo, pageSize); 
		List<Employee> findAll = emprepo.findAll(pageRequest).toList();
		if (findAll.isEmpty()) { 
			log.error(RECORD_NOT_FOUND);
			return new ResponseEntity<>(new CustomException(RECORD_NOT_FOUND), HttpStatus.UNPROCESSABLE_ENTITY);
		}
		Response response = new Response();
		response.setStatusCode(SUCCESS_STATUS);
		response.setStatus(SUCCESS);
		response.setMsg(GET_EMPLOYEES_DATA); 
		response.setData(findAll);
		return new ResponseEntity<>(response, HttpStatus.OK); 
	}

}
