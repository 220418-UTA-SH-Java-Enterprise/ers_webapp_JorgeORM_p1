package com.revature.services;

import java.util.List;
import java.util.Optional;

import com.revature.dao.ManagerDAO;
import com.revature.dao.ManagerDAOImpl;
import com.revature.models.Manager;

public class ManagerServiceImpl implements ManagerService{
	private ManagerDAO mdao;
	
	public ManagerServiceImpl(ManagerDAOImpl dao) {
		super();
		this.mdao = dao;
	}

	@Override
	public Manager login(String username, String password) {
		Optional<Manager> managers = mdao.selectAll()
			.stream()
			.filter(m -> (m.getUsername().equals(username) && m.getPassword().equals(password)))
			.findFirst();
		return (managers.isPresent() ? managers.get() : null);
	}

	@Override
	public int register(Manager manager) {
		return mdao.insert(manager);
	}

	@Override
	public Manager findUserById(int id) {
		return mdao.selectById(id);
	}

	@Override
	public Manager findUserByUsername(String username) {
		return mdao.selectByUsername(username);
	}

	@Override
	public List<Manager> findAllManagers() {
		return mdao.selectAll();
	}

	@Override
	public boolean editManager(Manager manager) {
		return mdao.update(manager);
	}

	@Override
	public boolean deleteManager(Manager manager) {
		return mdao.delete(manager);
	}

}
