package com.poc.model;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.persistence.Entity;

@Entity
@Table(name = "empcrud")
public class Employee {
	@Id
	private Integer id;
	//@Pattern(regexp = "^[a-zA-Z]{3,10}", message = "should not enter special characters and numbers and size should be minimum 3 for name")
	private String name;
	private Integer salary;
	private String city;
	private Integer phone;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=" + salary + ", city=" + city + ", phone=" + phone
				+ "]";
	}
}
