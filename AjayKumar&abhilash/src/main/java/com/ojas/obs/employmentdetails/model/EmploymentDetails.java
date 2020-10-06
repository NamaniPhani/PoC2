package com.ojas.obs.employmentdetails.model;

import javax.validation.constraints.NotNull;

public class EmploymentDetails {

	private int empId;
	@NotNull(message = "empName should not be empty")
	private String empName;

	@NotNull(message = "empSal should not be empty")
	private int empSal;

	@NotNull(message = "addres should not be empty")
	private String addres;

	@NotNull(message = "mobileNumber should not be empty")
	private int mobileNumber;

	public EmploymentDetails() {
		super();
	}

	public EmploymentDetails(int empId, String empName, int empSal, String addres, int mobileNumber) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empSal = empSal;
		this.addres = addres;
		this.mobileNumber = mobileNumber;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public int getEmpSal() {
		return empSal;
	}

	public void setEmpSal(int empSal) {
		this.empSal = empSal;
	}

	public String getAddres() {
		return addres;
	}

	public void setAddres(String addres) {
		this.addres = addres;
	}

	public int getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(int mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public String toString() {
		return "EmploymentDetails [empId=" + empId + ", empName=" + empName + ", empSal=" + empSal + ", addres="
				+ addres + ", mobileNumber=" + mobileNumber + "]";
	}

}