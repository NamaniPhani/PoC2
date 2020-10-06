package com.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.model.Employee;

public interface EmpRepository extends JpaRepository<Employee, Integer> {
	
}
