package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.CustomException;
import com.example.exception.Response;
import com.example.model.Employee;
import com.example.service.EmpServiceInf;

@RestController
public class EmployeeController {
	@Autowired
	private EmpServiceInf employeeService;

	@PostMapping(value = "/saveOrUpdate", produces = { "application/json", "application/xml" }, consumes = { "application/json", "application/xml" })
	public ResponseEntity<Object> saveOrUpdate(@Valid @RequestBody Employee employee) {
		ResponseEntity<Object> saveOrUpdate = employeeService.saveOrUpdate(employee);

		return saveOrUpdate;
	}

	@GetMapping(value = "/getAll/{pageNo}/{pageSize}", produces = { "application/json", "application/xml" })
	public ResponseEntity<Object> getAllEmployees(@PathVariable int pageNo, @PathVariable int pageSize) {
		return employeeService.getAllEmployees(pageNo, pageSize);
	}

	@GetMapping(value = "/getById/{id}", produces = "application/json", consumes = "application/json")
	public Response getById(@PathVariable("id") Integer id) {
		return employeeService.getById(id);

		/*
		 * if (byId != null) { return new ResponseEntity<Object>(byId, HttpStatus.OK); }
		 * else return new ResponseEntity<>(HttpStatus.BAD_REQUEST); }
		 */
	}

	@DeleteMapping(value = "/deleteById/{id}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> deleteById(@PathVariable("id") int id) {
		Response e = null;
		e = employeeService.getById(id);

		if (e == null) {
			return new ResponseEntity<Object>("Record with id" + id + "does not exist", HttpStatus.NOT_FOUND);
		} else {
			employeeService.deleteById(id);
			return new ResponseEntity<Object>("Record deleted successfully with id" + id, HttpStatus.OK);
		}

	}

	/*
	 * @PutMapping(value = "/updateById/{id}", produces = "application/json",
	 * consumes = "application/json") public ResponseEntity<Object>
	 * updateById(@RequestBody Employee employee, @PathVariable("id") int id) {
	 * Optional<Employee> e2 = employeeService.updateById(id); if (e2.get() == null)
	 * { return new ResponseEntity<Object>("Selected record not found" + id,
	 * HttpStatus.NOT_FOUND); } else if (e2 != id) { return new
	 * ResponseEntity<Object>(HttpStatus.BAD_REQUEST); } else {
	 * employeeService.updateById(id); return new
	 * ResponseEntity<Object>("Updated record with id" + id + " successfully.",
	 * HttpStatus.OK); } }
	 */

}
