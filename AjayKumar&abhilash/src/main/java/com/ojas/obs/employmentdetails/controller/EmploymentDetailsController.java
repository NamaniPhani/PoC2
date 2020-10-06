package com.ojas.obs.employmentdetails.controller;

import static com.ojas.obs.employmentdetails.constant.EmploymentDetailsConstants.GET;
import static com.ojas.obs.employmentdetails.constant.EmploymentDetailsConstants.SET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ojas.obs.employmentdetails.exception.DataNotInsertedException;
import com.ojas.obs.employmentdetails.exception.EmploymentDetailsException;
import com.ojas.obs.employmentdetails.facade.EmploymentDetailsFacade;
import com.ojas.obs.employmentdetails.model.EmploymentDetailsRequest;
import com.ojas.obs.employmentdetails.model.EmploymentDetailsResponse;

/**
 * resource class inserts,updates,deletes and retrieves data into/from
 * employment_details table
 * 
 * @author Ajar
 *
 */
@RestController
public class EmploymentDetailsController {
	@Autowired
	private EmploymentDetailsFacade employmentDetailsService;

	EmploymentDetailsResponse employementDetailsResponse = null;
	@PostMapping(SET)
	public ResponseEntity<Object> setEmploymentDetails(@RequestBody EmploymentDetailsRequest employmentDetailsRequest)
			throws EmploymentDetailsException, DataNotInsertedException {	

			if (null == employmentDetailsRequest) {
				throw new EmploymentDetailsException("The requested object is null");
			} else {
				employementDetailsResponse = employmentDetailsService.saveEmploymentDetails(employmentDetailsRequest);
			}
		return new ResponseEntity<>(employementDetailsResponse, HttpStatus.OK); 
	}
	@PostMapping(GET)
	public ResponseEntity<Object> getEmploymentDetails(@RequestBody EmploymentDetailsRequest employmentDetailsRequest)
			throws EmploymentDetailsException {
		if (null == employmentDetailsRequest) {
			throw new EmploymentDetailsException("The requested object is null");
		}
		 employementDetailsResponse = employmentDetailsService.viewEmploymentDetails(employmentDetailsRequest);
		return new ResponseEntity<>(employementDetailsResponse, HttpStatus.OK);

	}

}
