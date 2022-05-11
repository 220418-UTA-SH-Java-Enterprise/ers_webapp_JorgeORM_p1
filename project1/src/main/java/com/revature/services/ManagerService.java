package com.revature.services;
import java.util.List;

import com.revature.models.Manager;

public interface ManagerService {
	public Manager login(String username, String password);
	public int register(Manager manager);
	public Manager findUserById(int id);
	public Manager findUserByUsername(String username);
	public List<Manager> findAllManagers();
	public boolean editManager(Manager manager);
	public boolean deleteManager(Manager manager);
}
