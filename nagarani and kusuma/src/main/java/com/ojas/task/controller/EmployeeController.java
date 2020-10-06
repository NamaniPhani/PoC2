package com.ojas.task.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.ojas.task.model.Employee;
import com.ojas.task.model.Response;
import com.ojas.task.service.EmployeeService;

@RestController
public class EmployeeController {

	Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	EmployeeService service;

	@PostMapping(value = "/saveEmployee", produces = { "application/json",
			"application/xml" }, consumes = "application/json")
	public ResponseEntity<Object> save(@Valid @RequestBody Employee employee) {
		logger.debug("Inside save method of controller");
		Response response = null;
		if (employee != null) {
			response = service.save(employee);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			logger.debug("Inside save method else condition of controller");
			return new ResponseEntity<>("Invalid Employee", HttpStatus.CONFLICT);
		}
	}

	@GetMapping(value = { "/getById", "/getById/{id}" }, produces = { "application/json", "application/xml" })
	public ResponseEntity<Object> getById(@PathVariable(required = false) Integer id) {
		logger.debug("Inside getById method of controller");
		Response response = null;
		if (id == null) {
			response = new Response();
			response.setMsg("Id Should Not Be Null");
			response.setStatus("409");
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		} else {
			logger.debug("Inside getById method else condition of controller");

			response = service.findById(id);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@GetMapping(value = { "/getAll", "/getAll/{pageNo}/{pageSize}" }, produces = { "application/json",
			"application/xml" })
	public ResponseEntity<Object> getall(@PathVariable(required = false) Integer pageNo,
			@PathVariable(required = false) Integer pageSize) {
		logger.debug("Inside getall method of controller");
		Response response = null;
		if (pageNo == null || pageSize == null) {
			response = service.findAll();
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			logger.debug("Inside getall method else condition of controller");

			response = service.findAll(pageNo, pageSize);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@DeleteMapping(value = { "/delete", "/delete/{id}" }, produces = { "application/json", "application/xml" })
	public ResponseEntity<Object> delete(@PathVariable(required = false) Integer id) {
		logger.debug("Inside delete method of controller");
		Response response = null;
		if (id == null) {
			response = new Response();
			response.setMsg("Id Should Not Be Null");
			response.setStatus("409");
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		} else {
			logger.debug("Inside delete method else condition of controller");
			response = service.delete(id);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

	}
}
