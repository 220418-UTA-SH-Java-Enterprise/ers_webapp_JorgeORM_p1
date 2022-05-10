package com.revature.dao;

import java.util.List;
import com.revature.models.Manager;

public interface ManagerDAO {
	public int insert(Manager manager);
	
	public Manager selectById(int id);
	
	public Manager selectByUsername(String username);
	
	public List<Manager> selectAll();
	
	public boolean update(Manager manager);
	
	public boolean delete(Manager manager);
}
