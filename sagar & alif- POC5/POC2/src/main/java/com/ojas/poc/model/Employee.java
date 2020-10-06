package com.ojas.poc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "empcrud")
public class Employee {

	@Id
	private int id;
	@NotBlank(message = "Name Should Not Be Null Or Empty")
	@Pattern(regexp = "^[aA-zZ]\\w{2,30}$" ,message = "Given Name Is Not In Proper Format")
	private String name;
	@NotNull(message = "Salary Should Not Be Null")
	private int sal;
	@NotBlank(message = "City Should Not Be Null Or Empty")
	private String city;
	@NotNull(message = "Phone Should Not Be Null")
	private int phone;

	public Employee() {
		super();
	}

	public Employee(int id, String name, int sal, String city, int phone) {
		super();
		this.id = id;
		this.name = name;
		this.sal = sal;
		this.city = city;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", sal=" + sal + ", city=" + city + ", phone=" + phone + "]";
	}

}
