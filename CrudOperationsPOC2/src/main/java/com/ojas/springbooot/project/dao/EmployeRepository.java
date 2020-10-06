package com.ojas.springbooot.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ojas.springbooot.project.model.Employee;

public interface EmployeRepository extends JpaRepository<Employee, Integer> {

}
