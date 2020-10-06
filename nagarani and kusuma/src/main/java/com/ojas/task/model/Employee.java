package com.ojas.task.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "empcrud")
@XmlRootElement
public class Employee implements Serializable {
	@Id
	private Integer id;
	@NotBlank(message = "Name Should Not Be Null Or Empty")
	@Pattern(regexp = "^[aA-zZ]\\w{2,30}$", message = "Given Name Is Not In Proper Format")
	private String name;

	@NotNull(message = "Salary Should Not Be Null")
	private Integer sal;

	@NotBlank(message = "City Should Not Be Null Or Empty")
	private String city;

	@NotNull(message = "Phone Number Should Not Be Null")
	private Long phone;

	public Employee() {
		super();
	}  

	public Employee(Integer id,
			@NotBlank(message = "Name Should Not Be Null Or Empty") @Pattern(regexp = "^[aA-zZ]\\w{2,30}$", message = "Given Name Is Not In Proper Format") String name,
			@NotNull(message = "Salary Should Not Be Null") Integer sal,
			@NotBlank(message = "City Should Not Be Null Or Empty") String city,
			@NotNull(message = "Phone Should Not Be Null") Long phone) {
		super();
		this.id = id;
		this.name = name;
		this.sal = sal;
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

	public Integer getSal() {
		return sal;
	}

	public void setSal(Integer sal) {
		this.sal = sal;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", sal=" + sal + ", city=" + city + ", phone=" + phone + "]";
	}

}
