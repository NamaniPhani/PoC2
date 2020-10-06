package com.ojas.task.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ojas.task.model.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
	
	public Optional<Employee> findById(Integer id);
}
