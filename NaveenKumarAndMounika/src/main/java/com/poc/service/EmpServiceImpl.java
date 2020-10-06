package com.poc.service;

import java.util.Optional;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.poc.model.Employee;
import com.poc.repository.EmpRepository;
import com.poc.response.PagenationResponse;
import com.poc.response.Response;

@Service
public class EmpServiceImpl implements EmpServiceInf {
	private final Logger logger = Logger.getLogger(this.getClass());

	Response response = new Response();
	@Autowired
	private EmpRepository empRepository;

	@Override
	public Response saveOrUpdate(Employee employee) {
		logger.info("saveOrUpdate service");
		if (employee == null) {
			logger.error("please provide the data");
			response.setMessage("data not saved or updated successfully");
			response.setStatusCode("404");
			response.setEmpList(null);
		} else {
			Employee save = empRepository.save(employee);
			response.setMessage("data saved or updated successfully");
			response.setStatusCode("200");
			response.setEmpList(save);
			return response;
		}
		return response;
	}

	@Override
	public Response delete(Integer id) {
		logger.info("delete service" + id);
		if (id == null) {
			response.setMessage("please provide a valid ID");
			response.setStatusCode("404");
			return response;
		} else {
			empRepository.deleteById(id);
			response.setMessage("deleted record successfully");
			response.setStatusCode("200");
			response.setEmpList(id);
			return response;
		}
	}

	@Override
	public Response readById(Integer id) {
		logger.info("readById service" + id);

		Optional<Employee> findById = empRepository.findById(id);
		if ((findById.get().getId() == null) || (!findById.isPresent()) || id == null) {
			logger.error("please provide a ID or record not found");
			response.setMessage("please provide a ID or record not found");
			response.setStatusCode("404");
			response.setEmpList(null);
			return response;
		} else
			response.setMessage("data fetched successfully");
		response.setStatusCode("200");
		response.setEmpList(findById);
		return response;

	}

	@Override
	public PagenationResponse readAll(PagenationResponse pagenationResponse) {
		logger.info("readAll service");
		if (pagenationResponse.getPageNo() == null || pagenationResponse.getPageSize() == null) {
			logger.error("please provide the valid data");
			// throw new CustomException("please provide the valid data");
			pagenationResponse.setMessage("please provide  the valid data");
			pagenationResponse.setStatusCode("404");
			pagenationResponse.setEmpList(null);
			return pagenationResponse;
		} else {
			PageRequest page = PageRequest.of(pagenationResponse.getPageNo(), pagenationResponse.getPageSize(),
					org.springframework.data.domain.Sort.by("id"));
			Page<Employee> pagefindAll = empRepository.findAll(page);
			/*if (pagefindAll ==null) {
				// throw new CustomException("records not found");
				pagenationResponse.setMessage("please provide  the valid data");
				pagenationResponse.setStatusCode("404");
				pagenationResponse.setEmpList(null);
				return pagenationResponse;
			} */
				pagenationResponse.setPageNo(pagefindAll.getNumber());
				pagenationResponse.setPageSize(pagefindAll.getSize());
				pagenationResponse.setTotalRecords(pagefindAll.getTotalElements());
				pagenationResponse.setEmpList(pagefindAll.getContent());
				pagenationResponse.setMessage("data fetched successfully");
				pagenationResponse.setStatusCode("200");
				return pagenationResponse;
			}
		}
	}
