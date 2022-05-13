package com.revature.services;

import java.util.*;


import org.apache.log4j.Logger;

import com.revature.dao.EmployeeDAO;
import com.revature.dao.EmployeeDAOImpl;
import com.revature.models.Employee;

public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeDAO edao;
	private static Logger log = Logger.getLogger(EmployeeServiceImpl.class);
	
	public EmployeeServiceImpl(EmployeeDAOImpl dao) {
		super();
		this.edao = dao;
	}
	
	@Override
	public Employee login(String username, String password) {
		log.info("in service layer. Logging in Employee creds: " + username + ", " + password);
		
		Optional<Employee> employee = edao.selectAll()
				.stream()
				//ask for help
				//.filter(e -> (e.getUsername().equals(username) && e.getPassword(password)))
				.findFirst();
				
		return (employee.isPresent() ? employee.get() : null);
	}

	@Override
	public int register(Employee employee) {
		log.info("in service layer. Registering employee: " + employee);
		return edao.insert(employee);
	}

	@Override
	public Employee findEmployeeByID(int id) {
		log.info("in service layer. searching employee by id: " + id);
		return edao.selectById(id);
	}

	@Override
	public Employee findEmployeeByFirstName(String firstName) {
		log.info("in service layer. searching employee by first name: " + firstName);
		return edao.selectByFirstName(firstName);
	}

	@Override
	public List<Employee> findAllEmployees() {
		log.info("in service layer. finding all users...");
		return edao.selectAll();
	}

	@Override
	public boolean editEmployee(Employee employee) {
		log.info("in service layer. editing employee: " + employee);
		return edao.update(employee);
	}

	@Override
	public boolean deleteEmployee(Employee employee) {
		log.info("in service layer. deleting employee " + employee);
		return edao.delete(employee);
	}

}
