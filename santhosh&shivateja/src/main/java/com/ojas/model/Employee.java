package com.ojas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empcrud")
public class Employee {
	@Id
	private Integer id;

	private String name;
	@Column(name = "sal")
	private Double salary;

	private String city;

	private Integer phone;

	public Employee() {
		super();
	}

	public Employee(Integer id, String name, Double salary, String city, Integer phone) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.city = city;
		this.phone = phone;
	}

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

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
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

}
