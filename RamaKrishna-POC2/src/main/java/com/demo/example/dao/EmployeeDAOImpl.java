package com.demo.example.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.demo.example.entity.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public EmployeeDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Employee createEmployee(Employee employee) {
		String sql = "INSERT INTO empcrud(employeeId,employeeName, employeeSalary, employeeCity, employeePhoneNumber)"
				+ " VALUES (?,?,?,?,?)";
		jdbcTemplate.update(sql, employee.getEmployeeId(), employee.getEmployeeName(), employee.getEmployeeSalary(),
				employee.getEmployeeCity(), employee.getEmployeePhoneNumber());
		return employee;
	}

	@Override
	public Employee findEmployeeById(int contactId) {
		String sql = "SELECT * FROM empcrud WHERE employeeId=" + contactId;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Employee>() {

			@Override
			public Employee extractData(ResultSet rs) throws SQLException, DataAccessException {
				Employee employee = new Employee();
				if (rs.next()) {
					employee.setEmployeeId(rs.getInt("employeeId"));
					employee.setEmployeeName(rs.getString("employeeName"));
					employee.setEmployeeSalary(rs.getInt("employeeSalary"));
					employee.setEmployeeCity(rs.getString("employeeCity"));
					employee.setEmployeePhoneNumber(rs.getInt("employeePhoneNumber"));
					return employee;
				}
				return employee;
			}
		});
	}

	@Override
	public List<Employee> findAllEmployees() {
		String sql = "SELECT * FROM empcrud";
		List<Employee> listContact = jdbcTemplate.query(sql, new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee employee = new Employee();
				employee.setEmployeeId(rs.getInt("employeeId"));
				employee.setEmployeeName(rs.getString("employeeName"));
				employee.setEmployeeSalary(rs.getInt("employeeSalary"));
				employee.setEmployeeCity(rs.getString("employeeCity"));
				employee.setEmployeePhoneNumber(rs.getInt("employeePhoneNumber"));
				return employee;
			}
		});
		return listContact;
	}

	@Override
	public Employee UpdateEmployee(Employee employee, int employeeId) {
		if (employee.getEmployeeId() > 0) {
			String sql = "UPDATE empcrud set employeeName=?, employeeSalary=?, employeeCity=?,"
					+ "employeePhoneNumber=? WHERE employeeId=?";
			jdbcTemplate.update(sql, employee.getEmployeeName(), employee.getEmployeeSalary(),
					employee.getEmployeeCity(), employee.getEmployeePhoneNumber(), employee.getEmployeeId());
		}
		return employee;
	}

	@Override
	public String deleteEmployeeById(int contactId) {
		String sql = "DELETE FROM empcrud WHERE employeeId = ?";
		jdbcTemplate.update(sql, contactId);
		return "record deleted successfully!! ";
	}
 

	@Override
	public List<Employee> findEmployeesByPagination(List<Employee> getAllEmployees, int pageNo, int pageSize) {
		if (getAllEmployees != null && !getAllEmployees.isEmpty()) {
			pageSize = pageSize > 0 ? pageSize : pageSize * -1;
		pageNo = pageNo > 0 ? pageNo : pageNo == 0 ? 1 : pageNo * -1;
			if (pageSize != 0) {
				int endIndex = pageNo * pageSize;
				int startIndex = endIndex - pageSize;
				endIndex = endIndex < getAllEmployees.size() ? endIndex : getAllEmployees.size();
				startIndex = startIndex < getAllEmployees.size() ? startIndex : 0;
				getAllEmployees = getAllEmployees.subList(startIndex, endIndex);
			}
		}
		return getAllEmployees;
	}

	 

}
