package com.ojas.obs.employmentdetails.facade;

import static com.ojas.obs.employmentdetails.constant.EmploymentDetailsConstants.DELETE;
import static com.ojas.obs.employmentdetails.constant.EmploymentDetailsConstants.GETALL;
import static com.ojas.obs.employmentdetails.constant.EmploymentDetailsConstants.SAVE;
import static com.ojas.obs.employmentdetails.constant.EmploymentDetailsConstants.UPDATE;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.ojas.obs.employmentdetails.controller.EmploymentDetailsController;
import com.ojas.obs.employmentdetails.dao.EmploymentDetailsDAO;
import com.ojas.obs.employmentdetails.exception.DataNotInsertedException;
import com.ojas.obs.employmentdetails.exception.EmploymentDetailsException;
import com.ojas.obs.employmentdetails.model.EmploymentDetails;
import com.ojas.obs.employmentdetails.model.EmploymentDetailsRequest;
import com.ojas.obs.employmentdetails.model.EmploymentDetailsResponse;


@Service
public class EmploymentDetailsFacadeImpl implements EmploymentDetailsFacade {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmploymentDetailsController.class);

	@Autowired
	private EmploymentDetailsDAO employmentDetailsDAO;
	@Override
	public EmploymentDetailsResponse saveEmploymentDetails(EmploymentDetailsRequest employmentDetailsRequest)throws EmploymentDetailsException, DataNotInsertedException {
		EmploymentDetailsResponse employmentDetailsResponse = new EmploymentDetailsResponse();
		LOGGER.debug("the requested object is" + employmentDetailsRequest);

		if (StringUtils.isEmpty(employmentDetailsRequest.getTransactionType())
				|| CollectionUtils.isEmpty(employmentDetailsRequest.getEmploymentDetails())) {
			LOGGER.error("the requested object has null values" + employmentDetailsRequest);
			throw new EmploymentDetailsException("Requested object has null values");  
		}
		if (employmentDetailsRequest.getTransactionType().equalsIgnoreCase(SAVE)) {
			LOGGER.debug("response object is" + employmentDetailsRequest);
			return insertEmploymentDetails(employmentDetailsRequest.getEmploymentDetails(), employmentDetailsResponse);
		} else if (employmentDetailsRequest.getTransactionType().equalsIgnoreCase(UPDATE)) {
			LOGGER.debug("Response object is" + employmentDetailsRequest);
			return updateEmploymentDetails(employmentDetailsRequest.getEmploymentDetails(), employmentDetailsResponse);
		} else if (employmentDetailsRequest.getTransactionType().equalsIgnoreCase(DELETE)) { 
			LOGGER.debug("The Response object is" + employmentDetailsRequest);
			return deleteEmploymentDetails(employmentDetailsRequest.getEmploymentDetails(), employmentDetailsResponse);
		}

		employmentDetailsResponse.setStatusCode("400");
		employmentDetailsResponse.setStatusMessage("Requested transaction type is wrong");
		LOGGER.debug("Response Object Is" + employmentDetailsRequest);
		return employmentDetailsResponse;
	}

	
	private EmploymentDetailsResponse deleteEmploymentDetails(List<EmploymentDetails> employmentDetailsList,
			EmploymentDetailsResponse employmentDetailsResponse)
			throws DataNotInsertedException {
		employmentDetailsResponse.setStatusCode("201");
		employmentDetailsResponse.setStatusMessage("Data is not deleted");

		if (employmentDetailsDAO.deleteEmploymentDetails(employmentDetailsList)) {
			employmentDetailsResponse.setStatusCode("201");
			employmentDetailsResponse.setStatusMessage("Data is deleted successfully");
		}
		return employmentDetailsResponse;
	}

	
	private EmploymentDetailsResponse updateEmploymentDetails(List<EmploymentDetails> employmentDetailsList,
			EmploymentDetailsResponse employmentDetailsResponse)
			throws DataNotInsertedException {
		employmentDetailsResponse.setStatusCode("201");
		employmentDetailsResponse.setStatusMessage("Data is not updated");

		if (employmentDetailsDAO.updateEmploymentDetails(employmentDetailsList)) {
			employmentDetailsResponse.setStatusCode("201");
			employmentDetailsResponse.setStatusMessage("Data is updated successfully");
		}
		return employmentDetailsResponse;
	}

	private EmploymentDetailsResponse insertEmploymentDetails(List<EmploymentDetails> employmentDetailsList,
			EmploymentDetailsResponse employmentDetailsResponse)
			throws EmploymentDetailsException, DataNotInsertedException {
		employmentDetailsResponse.setStatusCode("201");
		employmentDetailsResponse.setStatusMessage("Data is not inserted successfully");
		for (EmploymentDetails employmentDetails2 : employmentDetailsList) {

			if (StringUtils.isEmpty(employmentDetails2.getEmpId()) || null == employmentDetails2.getEmpName()
					|| StringUtils.isEmpty(employmentDetails2.getEmpSal())){
				LOGGER.error("the requested object is null" + employmentDetails2);
				throw new EmploymentDetailsException("The requested object has null values");
				
				
			}
		} 
		if (employmentDetailsDAO.saveEmploymentDetails(employmentDetailsList)) {
			employmentDetailsResponse.setStatusCode("200");
			employmentDetailsResponse.setStatusMessage("Data is inserted successfully");
		}
		return employmentDetailsResponse;

	}

	@Override
	public EmploymentDetailsResponse viewEmploymentDetails(EmploymentDetailsRequest employmentDetailsRequest)
			throws EmploymentDetailsException {

		EmploymentDetailsResponse employmentDetailsResponse = new EmploymentDetailsResponse();
		employmentDetailsResponse.setStatusCode("200");
		employmentDetailsResponse.setStatusMessage("success");
		if (StringUtils.isEmpty(employmentDetailsRequest.getTransactionType())) {
			LOGGER.error("the requested object has null values" + employmentDetailsRequest);
			throw new EmploymentDetailsException("Requested object has null values");
		}
		List<EmploymentDetails> employmentDetailsList=new ArrayList<EmploymentDetails>();
		if (employmentDetailsRequest.getTransactionType().equalsIgnoreCase(GETALL)) {
			employmentDetailsList = employmentDetailsDAO.getAllEmploymentDetails();
			
			} if (employmentDetailsRequest.getTransactionType().equalsIgnoreCase("getById")){
				employmentDetailsList = employmentDetailsDAO.getEmploymentDetailsById(employmentDetailsRequest.getEmploymentDetails().get(0).getEmpId());
			}
			if(employmentDetailsList!=null) {
				employmentDetailsResponse.setEmploymentDetailsList(employmentDetailsList);
				employmentDetailsResponse.setStatusCode("200");
				employmentDetailsResponse.setStatusMessage("success");
			}else {
				
				employmentDetailsResponse.setStatusCode("409");
				employmentDetailsResponse.setStatusMessage("No records found");
			}
		return employmentDetailsResponse;

	}

}
