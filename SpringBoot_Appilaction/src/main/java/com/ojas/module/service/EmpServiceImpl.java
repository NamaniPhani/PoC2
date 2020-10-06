package com.ojas.module.service;

import static com.ojas.module.constants.EmpContants.EMPLOYEE_DELETE;
import static com.ojas.module.constants.EmpContants.EMPLOYEE_GETBYID;
import static com.ojas.module.constants.EmpContants.EMP_SAVE;
import static com.ojas.module.constants.EmpContants.EMP_FAIL;
import static com.ojas.module.constants.EmpContants.EMPDELETEBYID_FAIL;

import static com.ojas.module.constants.EmpContants.FAIL;
import static com.ojas.module.constants.EmpContants.RECORD_NOT_FOUND;
import static com.ojas.module.constants.EmpContants.SUCCESS;
import static com.ojas.module.constants.EmpContants.SUCCESS_STATUS;
import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ojas.module.exception.CustomException;
import com.ojas.module.exception.Response;
import com.ojas.module.model.Employee;
import com.ojas.module.repositories.EmpRepository;

@Service
public class EmpServiceImpl implements EmpService { 

	@Autowired
	private EmpRepository empRepo;
 
	static Logger log = Logger.getLogger(EmpServiceImpl.class.getName());

	@Override
	public ResponseEntity<Object> createEmp(Employee emp) throws Exception {
		log.debug("Incoming request employee service : " + emp);
		if (emp == null) {
			log.error(RECORD_NOT_FOUND);
			// throw new CustomException(RECORD_NOT_FOUND + emp);
			Employee save = empRepo.save(emp);
			Response response = new Response();
			response.setStatuscode("409");
			response.setStatus(FAIL); 
			response.setMessage(EMP_FAIL);
			response.setData(save);
			return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		Employee save = empRepo.save(emp);
		Response response = new Response();
		response.setStatuscode("200");
		response.setStatus(SUCCESS);
		response.setMessage(EMP_SAVE);
		response.setData(save);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getAllEmployees(int pageNo, int pageSize) {
		log.debug("Incoming request getAllEmployees");
		Sort sort = null;
		Pageable pageable = null;
		List<Employee> list = null;
		Response response = new Response();

		try {
			sort = Sort.by("name").ascending();
			pageable = PageRequest.of(pageNo, pageSize, sort);
			list = empRepo.findAll(pageable).toList();
			if (list.isEmpty())
				response.setMessage("No Records found");
			else {
				response.setData(list);
				response.setStatuscode(SUCCESS_STATUS);
				response.setStatus(SUCCESS);
				response.setMessage("Records Found");
			}
		} catch (Exception e) {
			log.error("Inside catch block of findAll method in service");
			response.setMessage("Provide Proper Data");
		}
		return new ResponseEntity<Object>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> deleteEmp(Integer id) {
		log.debug("Incoming request employee service delete method : " + id);
		if (id == null) {
			log.error(RECORD_NOT_FOUND);
			//throw new CustomException(RECORD_NOT_FOUND + id); 
			empRepo.deleteById(id);
			Response response = new Response();
			response.setStatuscode("409");
			response.setStatus(FAIL);
			response.setMessage(EMPDELETEBYID_FAIL +id); 
			return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);

		}
		empRepo.deleteById(id);
		Response response = new Response();
		response.setStatuscode(SUCCESS_STATUS);
		response.setStatus(SUCCESS);
		response.setMessage(EMPLOYEE_DELETE);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getById(Integer id) throws Exception {
		log.debug("Incoming request employee service getbyid method : " + id);
		Optional<Employee> findById = empRepo.findById(id);

		if (!findById.isPresent()) {
			log.error("RECORD_NOT_FOUND");
			throw new CustomException(RECORD_NOT_FOUND + id);
			// return new ResponseEntity<Object>(new CustomException(RECORD_NOT_FOUND),
			// HttpStatus.UNPROCESSABLE_ENTITY);
		}

		Response response = new Response();
		response.setStatuscode(SUCCESS_STATUS); 
		response.setStatus(SUCCESS);
		response.setMessage(EMPLOYEE_GETBYID);
		response.setData(findById);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
