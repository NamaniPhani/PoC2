package com.ojas.obs.employmentdetails.facade;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import com.ojas.obs.employmentdetails.dao.EmploymentDetailsDAO;
import com.ojas.obs.employmentdetails.exception.DataNotInsertedException;
import com.ojas.obs.employmentdetails.exception.EmploymentDetailsException;
import com.ojas.obs.employmentdetails.model.EmploymentDetails;
import com.ojas.obs.employmentdetails.model.EmploymentDetailsRequest;
import com.ojas.obs.employmentdetails.model.EmploymentDetailsResponse;

@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
public class EmploymentDetailsFacadeImplTest {

	@InjectMocks
	private EmploymentDetailsFacadeImpl employmentDetailsFacadeImpl;

	@Mock
	private EmploymentDetailsDAO employmentDetailsDAO;
    
	private EmploymentDetailsRequest employmentDetailsRequest = null;

	public List<EmploymentDetails> getEmploymentDetailsRequest() {
		List<EmploymentDetails> list = new ArrayList<>();
		EmploymentDetails ed = new EmploymentDetails();
		ed.setEmpId(1);
		ed.setEmpName("Ajay");
		ed.setAddres("Hyd");
		ed.setMobileNumber(789333);
		ed.setEmpSal(5000);
		list.add(ed);
		employmentDetailsRequest = new EmploymentDetailsRequest();
		employmentDetailsRequest.setTransactionType("save");
		employmentDetailsRequest.setEmploymentDetails(list);
		return list;

	}
//save
	@Test
	public void testSaveEmployementDetailsForInsert() throws DataNotInsertedException, EmploymentDetailsException {
		employmentDetailsRequest = new EmploymentDetailsRequest();
		employmentDetailsRequest.setTransactionType("save");
		employmentDetailsRequest.setEmploymentDetails(Arrays.asList(new EmploymentDetails()));
		EmploymentDetailsResponse emplDetailsResponse = new EmploymentDetailsResponse();
		emplDetailsResponse.setStatusCode("201");
		emplDetailsResponse.setStatusMessage("Data is inserted successfully");
		when(employmentDetailsDAO.saveEmploymentDetails(getEmploymentDetailsRequest())).thenReturn(true);
		assertEquals("200",	employmentDetailsFacadeImpl.saveEmploymentDetails(employmentDetailsRequest).getStatusCode());
		}
	
	
	//TransationTypeCheck
	@Test(expected=EmploymentDetailsException.class)
	public void transationTypeTest() throws EmploymentDetailsException, DataNotInsertedException {
			List<EmploymentDetails> list = new ArrayList<>();
			EmploymentDetails ed = new EmploymentDetails();
			list.add(ed);
			list.isEmpty();
			employmentDetailsRequest = new EmploymentDetailsRequest();
			employmentDetailsRequest.setTransactionType(null);
			employmentDetailsRequest.setEmploymentDetails(list);
		assertNotEquals(employmentDetailsFacadeImpl.saveEmploymentDetails(employmentDetailsRequest),HttpStatus.OK);
		
	}
	
//update
	@Test
	public void testSaveEmployementDetailsForUpdate() throws DataNotInsertedException, EmploymentDetailsException {
		employmentDetailsRequest = new EmploymentDetailsRequest();
		employmentDetailsRequest.setTransactionType("update");
		employmentDetailsRequest.setEmploymentDetails(Arrays.asList(new EmploymentDetails()));
		EmploymentDetailsResponse emplDetailsResponse = new EmploymentDetailsResponse();
		emplDetailsResponse.setStatusCode("201");
		emplDetailsResponse.setStatusMessage("Data is updated successfully");
		when(employmentDetailsDAO.updateEmploymentDetails((List<EmploymentDetails>) Matchers.anyCollectionOf(EmploymentDetails.class))).thenReturn(true);
		assertEquals(emplDetailsResponse.getStatusCode(),
		employmentDetailsFacadeImpl.saveEmploymentDetails(employmentDetailsRequest).getStatusCode());
		assertEquals(emplDetailsResponse.getStatusMessage(),employmentDetailsFacadeImpl.saveEmploymentDetails(employmentDetailsRequest).getStatusMessage());
	}
//delete
	@Test
	public void testSaveEmployementDetailsForDelete() throws DataNotInsertedException, EmploymentDetailsException {
		employmentDetailsRequest = new EmploymentDetailsRequest();
		employmentDetailsRequest.setTransactionType("delete");
		employmentDetailsRequest.setEmploymentDetails(Arrays.asList(new EmploymentDetails()));
		EmploymentDetailsResponse emplDetailsResponse = new EmploymentDetailsResponse();
		emplDetailsResponse.setStatusCode("201");
		emplDetailsResponse.setStatusMessage("Data is deleted successfully");
		when(employmentDetailsDAO.deleteEmploymentDetails((List<EmploymentDetails>) Matchers.anyCollectionOf(EmploymentDetails.class))).thenReturn(true);
		assertEquals(emplDetailsResponse.getStatusCode(),employmentDetailsFacadeImpl.saveEmploymentDetails(employmentDetailsRequest).getStatusCode());
		assertEquals(emplDetailsResponse.getStatusMessage(),employmentDetailsFacadeImpl.saveEmploymentDetails(employmentDetailsRequest).getStatusMessage());
	}
//tTranastion type check
	@Test
	public void testSaveEmployementDetailsWithNoTransactionType()throws DataNotInsertedException, EmploymentDetailsException {
		employmentDetailsRequest = new EmploymentDetailsRequest();
		employmentDetailsRequest.setTransactionType("insert");
		employmentDetailsRequest.setEmploymentDetails(Arrays.asList(new EmploymentDetails()));
		EmploymentDetailsResponse emplDetailsResponse = new EmploymentDetailsResponse();
		emplDetailsResponse.setStatusCode("400");
        emplDetailsResponse.setStatusMessage("Requested transaction type is wrong");
		assertEquals(emplDetailsResponse.getStatusCode(),employmentDetailsFacadeImpl.saveEmploymentDetails(employmentDetailsRequest).getStatusCode());
		assertEquals(emplDetailsResponse.getStatusMessage(),employmentDetailsFacadeImpl.saveEmploymentDetails(employmentDetailsRequest).getStatusMessage());

	}
	//getAll

	@Test
	public void testViewEmployementDetails() throws EmploymentDetailsException {
		employmentDetailsRequest = new EmploymentDetailsRequest();
		employmentDetailsRequest.setTransactionType("getAll");
		EmploymentDetailsResponse emplDetailsResponse = new EmploymentDetailsResponse();
		emplDetailsResponse.setStatusCode("200");
		emplDetailsResponse.setStatusMessage("Employee details found");
		emplDetailsResponse.setEmploymentDetailsList(Arrays.asList(new EmploymentDetails()));
		when(employmentDetailsDAO.getAllEmploymentDetails()).thenReturn(emplDetailsResponse.getEmploymentDetailsList());
		assertEquals(emplDetailsResponse.getStatusCode(),employmentDetailsFacadeImpl.viewEmploymentDetails(employmentDetailsRequest).getStatusCode());
	}
	

	

}
