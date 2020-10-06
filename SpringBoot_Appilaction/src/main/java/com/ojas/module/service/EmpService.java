package com.ojas.module.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ojas.module.model.Employee;

@Service
public interface EmpService {

	public ResponseEntity<Object> createEmp(Employee emp) throws Exception;
	public ResponseEntity<Object> getAllEmployees(int pageNo,int pageSIze) throws Exception;
    public ResponseEntity<Object> deleteEmp(Integer id);
    public ResponseEntity<Object> getById(Integer id)throws Exception;


}
