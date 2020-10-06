//
//package com.ojas.obs.employmentdetails.dao;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.mockito.ArgumentMatchers.anyList;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Spy;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import com.ojas.obs.employmentdetails.exception.DataNotInsertedException;
//import com.ojas.obs.employmentdetails.facade.EmploymentDetailsFacadeImpl;
//import com.ojas.obs.employmentdetails.model.EmploymentDetails;
//import com.ojas.obs.employmentdetails.model.EmploymentDetailsRequest;
//import com.ojas.obs.employmentdetails.model.EmploymentDetailsResponse;
//import com.ojas.obs.employmentdetails.util.QueryUtil;
//
//@RunWith(MockitoJUnitRunner.class)
//public class EmploymentDetailsDAOImplTest {
//
//	@InjectMocks
//	EmploymentDetailsDAOImpl employmentDetailsDAOImpl;
//
//	@Mock
//	private EmploymentDetailsFacadeImpl facade;
//
//	@Mock
//	private JdbcTemplate jdbcTemplate;
//
//	@Mock
//	private QueryUtil QueryUtil;
//
//	@Spy
//	private EmploymentDetailsRequest employmentDetailsRequest;
//
//	@Spy
//	private EmploymentDetails employmentDetails;
//
//	@Spy
//	private EmploymentDetailsResponse emplDetailsResponse;
//
//	public List<EmploymentDetails> getModel() {
//		List<EmploymentDetails> list = new ArrayList<EmploymentDetails>();
//		EmploymentDetails model = new EmploymentDetails();
//		model.setEmpId(1);
//		model.setEmpName("aJAY");
//		model.setEmpSal(5000);
//		model.setAddres("Ban");
//		model.setMobileNumber(458766);
//		list.add(model);
//		return list;
//	}

	/*
	 * @Test public void saveEmployee() throws DataNotInsertedException {
	 * EmploymentDetailsRequest employmentDetailsRequest=new
	 * EmploymentDetailsRequest();
	 * employmentDetailsRequest.setEmploymentDetails(getModel());
	 * employmentDetailsRequest.setPageNo(1);
	 * employmentDetailsRequest.setPageSize(2);
	 * employmentDetailsRequest.setTransactionType("save"); int[] count = { 1, 2
	 * ,3,4,5};
	 * when(jdbcTemplate.batchUpdate(anyString(),anyList())).thenReturn(count);
	 * boolean status =
	 * employmentDetailsDAOImpl.saveEmploymentDetails(employmentDetailsRequest);
	 * assertEquals(true, status); }
	 * 
	 * @Test public void updateEmployee() throws DataNotInsertedException {
	 * List<EmploymentDetails> li = new ArrayList<EmploymentDetails>();
	 * EmploymentDetails emp = new EmploymentDetails(); emp.setAddres("ban");
	 * emp.setEmpId(1); emp.setEmpName("Ajay");
	 * 
	 * emp.setMobileNumber(78944);
	 * 
	 * li.add(emp); int update = 0; for (EmploymentDetails employmentDetails : li) {
	 * update =
	 * jdbcTemplate.update(QueryUtil.getQuery("INSERT_EMPLOYMENT_DETAILS_STMT"),
	 * employmentDetails.getEmpId(), employmentDetails.getEmpName(),
	 * employmentDetails.getEmpSal(), employmentDetails.getAddres(),
	 * employmentDetails.getMobileNumber()); } assertNotNull(update);
	 * 
	 * //when(EmploymentDetailsDAOImpl.updateEmploymentDetails(li)).thenReturn(true)
	 * ;
	 * 
	 * }
	 */

