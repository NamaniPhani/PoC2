package com.poc5.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.poc5.model.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Integer> {

	@Query(value = "select * from poc5.empcrud where id = ?1", nativeQuery = true)
	public Employee readEmployee(int id);

	@Transactional
	@Modifying
	@Query(value = "update poc5.empcrud set status = '0' where id = :id", nativeQuery = true)
	public int deleteEmployee(int id);
}