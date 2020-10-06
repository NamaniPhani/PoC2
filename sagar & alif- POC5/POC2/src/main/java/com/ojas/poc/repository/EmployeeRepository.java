package com.ojas.poc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ojas.poc.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	@Query(value = "select * from jwt.empcrud e where e.NAME like %?1%" , nativeQuery = true)
	List<Employee> findByName(String name);
	
}	
