package com.ojas.module.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.module.model.Employee;
import com.ojas.module.service.EmpServiceImpl;

@RestController
@RequestMapping("/emp")
public class EmpController {    

	@Autowired
	private EmpServiceImpl empService;
 
	static Logger log = Logger.getLogger(EmpController.class.getName());

	@PostMapping("/createemp") 
	public ResponseEntity<Object> createEmp(@Valid @RequestBody Employee empReq) throws Exception {
		log.debug("Incoming request employee controller : " + empReq);
		return empService.createEmp(empReq);
	} 
  
	@GetMapping("/getallemployess/{pageNo}/{pageSize}") 
	public ResponseEntity<Object> getAllEmployees(@PathVariable int pageNo,@PathVariable int pageSize) throws Exception{
		log.debug("Incoming request getallemployees method ");
		ResponseEntity<Object> getAll =empService.getAllEmployees(pageNo,pageSize);
		return new ResponseEntity<>(getAll,HttpStatus.OK); 
  
	}
 
	@DeleteMapping("/deleteemp/{id}")
	public ResponseEntity<Object> deleteEmp(@PathVariable Integer id) {
		log.debug("Incomming request deleteemployee method");
		return empService.deleteEmp(id); 
 
	}
 
	@GetMapping("/getById/{id}")
	public ResponseEntity<Object> getById(@PathVariable Integer id)throws Exception{
		log.debug("Incomming request getbyid method");
		ResponseEntity<Object> getByIdEmp =empService.getById(id);
		return new ResponseEntity<Object>(getByIdEmp,HttpStatus.OK);
 
	}
	
	  
}
