package com.ojas.controller;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.exception.RecordNotFoundException;
import com.ojas.model.Employee;
import com.ojas.response.EmployeeResponseData;
import com.ojas.response.Response;
import com.ojas.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	private static final Logger logger = LogManager.getLogger(EmployeeController.class);

	@PostMapping(value = "/addemployee", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
		try {
			if (employee == null) {
				logger.info(" employee request object is null");
				Response response = new Response();
				response.setMessage("employee object shouldn't null");
				response.setStatusCode("422");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}

			if (employee.getName().isEmpty()  || employee.getCity().isEmpty() || employee.getPhone() == null || employee.getSalary() == null) {
				logger.info(" employee request feilds are null");
				Response response = new Response();
				response.setMessage("employee request feilds shouldn't null");
				response.setStatusCode("422");
				return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
			}

			Employee createEmployee = employeeService.createEmployee(employee);

			if (createEmployee == null) {
				logger.info("employee not successfully created");
				Response response = new Response();
				response.setMessage("employee not successfully created");
				response.setStatusCode("409");
				return new ResponseEntity<Response>(response, HttpStatus.CONFLICT);
			} else {
				logger.info("employee successfully created");
				Response response = new Response();
				response.setMessage("employee successfully created");
				response.setStatusCode("200");
				return new ResponseEntity<Response>(response, HttpStatus.OK);
			}

		} catch (SQLException e) {

			logger.info(" sql exception occured", e);
			Response response = new Response();
			response.setMessage("Sql Exception Occured");
			response.setStatusCode("409");
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);

		} catch (Exception e) {

			logger.info("exception occured", e);
			Response response = new Response();
			response.setMessage("Exception Occured");
			response.setStatusCode("409");
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}

	}

	@GetMapping(value = "/getemployeebyid/{id}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> getEmployeeById(@PathVariable("id") Integer id) {
		try {
			if (id == null) {
				logger.info(" employee id is null");
				Response response = new Response();
				response.setMessage("employee id shouldn't null");
				response.setStatusCode("422");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}

			Employee employeeById = employeeService.getEmployeeById(id);

			logger.info("records found with id ", id);

			return new ResponseEntity<>(employeeById, HttpStatus.OK);

		} catch (RecordNotFoundException e) {
			logger.info("No records found with id ", id, e);
			Response response = new Response();
			response.setMessage("No records found with " + id);
			response.setStatusCode("409");
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}

		catch (Exception e) {

			logger.info("exception occured", e);
			Response response = new Response();
			response.setMessage("Exception Occured");
			response.setStatusCode("409");
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}

	}

	@PutMapping(value = "/updateemployee", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
		try {
			if (employee == null) {
				logger.info(" employee request object is null");
				Response response = new Response();
				response.setMessage("employee object shouldn't null");
				response.setStatusCode("422");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}

			if (employee.getId() == null || employee.getName().isEmpty() || employee.getName() == null
					|| employee.getCity().isEmpty() || employee.getCity() == null || employee.getPhone() == null
					|| employee.getSalary() == null) {
				logger.info(" employee request feilds are null");
				Response response = new Response();
				response.setMessage("employee request feilds shouldn't null");
				response.setStatusCode("422");
				return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
			}

			Employee employee2 = employeeService.updateEmployee(employee);

			Response response = new Response();

			if (employee2 == null) {
				logger.info("employee not successfully updated");
				response.setMessage("employee not successfully updated");
				response.setStatusCode("409");
				return new ResponseEntity<Response>(response, HttpStatus.CONFLICT);
			} else {
				logger.info("employee successfully updated");
				response.setMessage("employee successfully updated");
				response.setStatusCode("200");
				return new ResponseEntity<Response>(response, HttpStatus.OK);
			}

		} catch (RecordNotFoundException e) {

			logger.info(" record not found exception", e);
			Response response = new Response();
			response.setMessage("No records found to update");
			response.setStatusCode("409");
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);

		}

		catch (SQLException e) {

			logger.info(" sql exception occured", e);
			Response response = new Response();
			response.setMessage("Sql Exception Occured");
			response.setStatusCode("409");
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);

		} catch (Exception e) {

			logger.info("exception occured", e);
			Response response = new Response();
			response.setMessage("Exception Occured");
			response.setStatusCode("409");
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}

	}

	@DeleteMapping(value = "/getemployeebyid/{id}", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> deleteEmployeeById(@PathVariable("id") Integer id) {
		try {
			if (id == null) {
				logger.info(" employee id is null");
				Response response = new Response();
				response.setMessage("employee id shouldn't null");
				response.setStatusCode("422");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}

			employeeService.deleteEmployeeById(id);
			Response response = new Response();
			logger.info("employee successfully deleted with ", id);
			response.setMessage("employee successfully deleted");
			response.setStatusCode("200");
			return new ResponseEntity<Response>(response, HttpStatus.OK);

		} catch (RecordNotFoundException e) {

			logger.info(" record not found exception", e);
			Response response = new Response();
			response.setMessage("No records found to update");
			response.setStatusCode("409");
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);

		} catch (SQLException e) {

			logger.info(" sql exception occured", e);
			Response response = new Response();
			response.setMessage("Sql Exception Occured");
			response.setStatusCode("409");
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);

		} catch (Exception e) {

			logger.info("exception occured", e);
			Response response = new Response();
			response.setMessage("Exception Occured");
			response.setStatusCode("409");
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}

	}

	@GetMapping(path = { "name/{pageNo}/{sizePerPage}/{searchByName}" })
	public ResponseEntity<?> getAllEmployeeInfo(
			@PathVariable(required = false, name = "searchByName") String searchByName,
			@PathVariable("pageNo") Integer pageNo, @PathVariable("sizePerPage") Integer sizePerPage) {
		if (searchByName.equalsIgnoreCase("null") || searchByName.equalsIgnoreCase("undefined")) {
			searchByName = null;
		}
		if (pageNo == null || sizePerPage == null) {
			logger.error("Object is null  in post");
			Response response = new Response();

			response.setMessage("page number and size per page shouldnot null");
			response.setStatusCode("400");

			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		try {
			EmployeeResponseData listOfEmployee = null;

			if (searchByName == null) {
				listOfEmployee = employeeService.getAllEmployeeDetails(pageNo, sizePerPage);
			}

			else {
				listOfEmployee = employeeService.getAllEmployeeDetails(searchByName, pageNo, sizePerPage);
			}

			logger.info("getting list of records" + listOfEmployee);

			return new ResponseEntity<>(listOfEmployee, HttpStatus.OK);
		} catch (Exception e) {

			logger.debug("inside catch block " + e.getMessage());
			Response response = new Response();
			response.setMessage("Exception caught");
			response.setStatusCode("409");
			e.printStackTrace();
			return new ResponseEntity<>(response, HttpStatus.CONFLICT);
		}

	}

}
