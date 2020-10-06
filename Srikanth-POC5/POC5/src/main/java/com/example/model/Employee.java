package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="empcrud")
public class Employee {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name="ID")
private int id;
@Column(name="NAME")
private String name;
@Column(name="SAL")
private Integer salary;
@Column(name="CITY")
private String city;
@Column(name="PHONE")
private String phonenumber;

}
