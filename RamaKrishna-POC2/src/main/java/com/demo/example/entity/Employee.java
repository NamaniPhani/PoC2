package com.demo.example.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Employee {
	private Integer employeeId;
	@NotNull
	@NotBlank
	@NotEmpty
	private String employeeName;
	@NotNull(message = "Please enter valid salary")
	@NotBlank
	@NotEmpty
	private Integer employeeSalary;
	@NotNull
	@NotBlank
	@NotEmpty
	private String employeeCity;
	@NotNull
	@NotBlank
	@NotEmpty
	private Integer employeePhoneNumber;

	public Employee() {
		super();
	}

	public Employee(Integer employeeId, String employeeName, Integer employeeSalary, String employeeCity,
			Integer employeePhoneNumber) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeSalary = employeeSalary;
		this.employeeCity = employeeCity;
		this.employeePhoneNumber = employeePhoneNumber;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Integer getEmployeeSalary() {
		return employeeSalary;
	}

	public void setEmployeeSalary(Integer employeeSalary) {
		this.employeeSalary = employeeSalary;
	}

	public String getEmployeeCity() {
		return employeeCity;
	}

	public void setEmployeeCity(String employeeCity) {
		this.employeeCity = employeeCity;
	}

	public Integer getEmployeePhoneNumber() {
		return employeePhoneNumber;
	}

	public void setEmployeePhoneNumber(Integer employeePhoneNumber) {
		this.employeePhoneNumber = employeePhoneNumber;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeeSalary="
				+ employeeSalary + ", employeeCity=" + employeeCity + ", employeePhoneNumber=" + employeePhoneNumber
				+ "]";
	}

}
