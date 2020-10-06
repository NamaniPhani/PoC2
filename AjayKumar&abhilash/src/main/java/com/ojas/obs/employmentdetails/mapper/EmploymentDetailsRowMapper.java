package com.ojas.obs.employmentdetails.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ojas.obs.employmentdetails.model.EmploymentDetails;

public class EmploymentDetailsRowMapper implements RowMapper<EmploymentDetails> {

	@Override
	public EmploymentDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmploymentDetails employmentDetails = new EmploymentDetails();
		employmentDetails.setEmpId(rs.getInt("empId"));
		employmentDetails.setEmpName(rs.getString("empName"));
		employmentDetails.setEmpSal(rs.getInt("empSal"));;
		employmentDetails.setAddres(rs.getString("addres"));
		employmentDetails.setMobileNumber(rs.getInt("mobileNumber"));
		
		return employmentDetails;
	}

}
