package com.poc.service;
import com.poc.model.Employee;
import com.poc.response.PagenationResponse;
import com.poc.response.Response;

public interface EmpServiceInf {
	public Response saveOrUpdate(Employee employee);

	public Response delete(Integer id);

	public Response readById(Integer id);
	
	public PagenationResponse readAll(PagenationResponse pagenationResponse);
}
