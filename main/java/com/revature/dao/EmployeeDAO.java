package com.revature.dao;

import java.util.List;

import com.revature.models.Employee;

public interface EmployeeDAO {
	
	public int insert(Employee employee);
	
	public Employee selectById(int id);
	
	public Employee selectByFirstName(String firstName);
	
	public List<Employee> selectAll();
	
	public boolean update(Employee employee);
	
	public boolean delete(Employee employee);

}
