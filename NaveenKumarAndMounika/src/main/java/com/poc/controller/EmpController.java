package com.poc.controller;

import javax.validation.Valid;
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

import com.poc.model.Employee;
import com.poc.response.PagenationResponse;
import com.poc.response.Response;
import com.poc.service.EmpServiceInf;

@RestController
@RequestMapping("/emp")
public class EmpController {

	@Autowired
	private EmpServiceInf empService;

	@PostMapping(value = "/saveOrUpdate", produces = { "application/json", "application/xml" }, consumes = {
			"application/json", "application/xml" })
	public ResponseEntity<Object> saveOrUpdate(@Valid @RequestBody Employee employee) {

		Response saveOrUpdate = empService.saveOrUpdate(employee);
		if (saveOrUpdate != null) {
			return new ResponseEntity<>(saveOrUpdate, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = "/read/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Object> readById(@PathVariable Integer id) {
		Response readById = empService.readById(id);
		if (readById != null) {
			return new ResponseEntity<>(readById, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	@GetMapping(value = "/readAll/{pageNo}/{pageSize}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Object> readAll(PagenationResponse paginationResponse) {
		PagenationResponse readAll = empService.readAll(paginationResponse);
		if (readAll != null) {
			return new ResponseEntity<>(readAll, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(value = "/delete/{id}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Object> delete(@PathVariable Integer id) {
		if (id != null) {
			Response delete = empService.delete(id);
			return new ResponseEntity<>(delete, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
