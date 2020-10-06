package com.ServiceTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.poc.model.Employee;
import com.poc.repository.EmpRepository;
import com.poc.response.PagenationResponse;
import com.poc.response.Response;
import com.poc.service.EmpServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class EmpServiceTest {
	@InjectMocks
	private EmpServiceImpl empService;

	@Mock
	private EmpRepository empRepository;

	@Spy
	Response response = new Response();
	@Spy
	PagenationResponse presponse=new PagenationResponse();
	
	@Spy
	Employee employee = new Employee();

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(empService.getClass());
	}

	@Test
	public void save_ok() {
		employee.setId(1);
		employee.setName("naveen");
		employee.setCity("hyderabad");
		employee.setPhone(1234);
		employee.setSalary(1234);
		response.setStatusCode("200");
		response.setEmpList(employee);
		response.setMessage("success");
		when(empRepository.save(employee)).thenReturn(employee);

		Response saveOrUpdate = empService.saveOrUpdate(employee);
		assertEquals("200",saveOrUpdate.getStatusCode());
	}

	@Test
	public void testSave_Conflict() {
		response.setStatusCode("409");
		response.setMessage("conflict");
		response.setEmpList(null);
		when(empRepository.save(null)).thenReturn(response);
		Response saveOrUpdate = empService.saveOrUpdate(null);
		assertEquals("404",saveOrUpdate.getStatusCode());
	}
	 
	@Test
    public void readById() {
		employee.setId(1);
		employee.setName("naveen");
		employee.setCity("hyderabad");
		employee.setPhone(1234);
		employee.setSalary(1234);
        Optional<Employee> employee1 = Optional.of(employee);
        when(empRepository.findById(1)).thenReturn(employee1);
       Response getById = empService.readById(1);
        assertEquals("200",getById.getStatusCode());
    }
	
	@Test
	public void readById_Conflict() {
		employee.setId(null);
		Optional<Employee> employee1 = Optional.of(employee);
		when(empRepository.findById(employee.getId())).thenReturn(employee1);
		Response readById = empService.readById(employee.getId());
		System.out.println(readById);
		assertEquals("404", readById.getStatusCode()); 
	}
	
	@Test
	public void readAllTest() {
		Employee employee=new Employee();
		employee.setId(1);
		employee.setName("naveen");
		employee.setCity("hyderabad");
		employee.setPhone(1234);
		employee.setSalary(1234);

		PagenationResponse presponse = new PagenationResponse();
		presponse.setStatusCode("200");
		presponse.setMessage("success");
		presponse.setPageNo(0);
		presponse.setPageSize(2);
		presponse.setEmpList(employee);
		
		List<Employee> list = new ArrayList<>();
		list.add(employee);
		Page<Employee> page =new PageImpl<>(list);
		
		when(empRepository.findAll(org.mockito.Matchers.isA(Pageable.class))).thenReturn(page);
		
		PagenationResponse readAll = empService.readAll(presponse);
		System.out.println(readAll);
		assertEquals("200",readAll.getStatusCode());
	}
	
	@Test
	public void readAllTest_Conflict(){
		presponse.setStatusCode("404");
		presponse.setMessage("conflict");
		presponse.setEmpList(null);
		Page<Employee> page = Mockito.mock(Page.class);
		when(empRepository.findAll(org.mockito.Matchers.isA(Pageable.class))).thenReturn(page);
		PagenationResponse readAll = empService.readAll(presponse);
		assertEquals("404",readAll.getStatusCode());
	}
	
	@Test
	public void readAllTest_IsEmpty(){
		presponse.setStatusCode("404");
		presponse.setEmpList(null);
	PagenationResponse readAll = empService.readAll(presponse);
		assertEquals("404",readAll.getStatusCode());
	}
		
	@Test
    public void testDelete() {
        Response response = empService.delete(1);
        assertEquals("200", response.getStatusCode());
    }
	
	@Test
	public void delete_Conflict(){
		Response response=empService.delete(null);
		assertEquals("404",response.getStatusCode());
		
	}
	
}
