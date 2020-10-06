package com.ojas.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ojas.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

	Page<Employee> findAll(Pageable pageRequest);

	

}
