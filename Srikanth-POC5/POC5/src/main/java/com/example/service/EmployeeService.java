package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.model.Employee;
import com.example.repository.EmployeeRepository;

import com.example.response.SuccessResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Service
public class EmployeeService { 
	
	@Autowired
	private EmployeeRepository employeeRepository;
	        SuccessResponse successResponse=new SuccessResponse();
	        Logger logger = Logger.getLogger(this.getClass());
	        
	        
	public SuccessResponse saveEmployee(Employee employee)
	{   
		try
		{
			List<Employee> listOfEmployee=new ArrayList<>();
		
		Employee employeeObject = employeeRepository.save(employee);
		listOfEmployee.add(employeeObject);
		if(employeeObject!=null)
		{    successResponse.setStatusCode("200");
		     successResponse.setStatusMessage("EmployeeObject is Created Succesfully");
		     successResponse.setEmployeeList(listOfEmployee);
		     return successResponse;
		}
		else
		{	
		 successResponse.setStatusCode("422");
	     successResponse.setStatusMessage("Failed to create EmployeeObject");
	     successResponse.setEmployeeList(listOfEmployee);
		
	      return successResponse;
		}
		}
		catch (Exception e) {
			logger.error("error getJobById EmployeeService ::::" + e.getMessage());
			successResponse.setStatusCode("422");
			successResponse.setStatusMessage(e.getMessage());
			
		 return successResponse;
		}
		
	}
	
	public SuccessResponse getAllEmployeeObject(int pageNo,int pagesize)
	{
		
		 Pageable paging = PageRequest.of(pageNo, pagesize);
		 List<Employee> findAll = employeeRepository.findAll(paging).toList();
	      
		if(!findAll.isEmpty())
		{
			successResponse.setStatusMessage("List Of Employee ");
			successResponse.setStatusCode("200");
			successResponse.setEmployeeList(findAll);
			return successResponse;
		}
		else
		{
			successResponse.setStatusMessage("Failed To List Of Employee");
			successResponse.setStatusCode("422");
			successResponse.setEmployeeList(findAll);
			return successResponse;
		}
	}
	
	public SuccessResponse deleteById(int id)
	{
		
		try
		{
			employeeRepository.deleteById(id); 
		
		successResponse.setStatusCode("200");
		successResponse.setStatusMessage("Deleted Succesfully");
		return  successResponse;
		}
		catch(Exception e)
		{
			successResponse.setStatusCode("422");
			successResponse.setStatusMessage(e.getMessage());
			
			successResponse.setEmployeeList(null);
			
			 
			return successResponse;
		}
		 
	}
	
	public SuccessResponse edit(Employee employee)
	
	{   
		
		List<Employee> listOfEmployee=new ArrayList<>();
		Employee employeeObject = employeeRepository.save(employee);
		listOfEmployee.add(employeeObject);
		if(employeeObject!=null)
		{
			successResponse.setStatusCode("200");
			successResponse.setStatusMessage("Updated Employee Data Successfully");
			successResponse.setEmployeeList(listOfEmployee);
			
			 return successResponse;
		}
		 successResponse.setStatusCode("422");
	     successResponse.setStatusMessage("Failed to Updated EmployeeObject");
	     successResponse.setEmployeeList(listOfEmployee);
		
	        return successResponse;
	}

}
