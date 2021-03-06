package com.revature.services;
import java.util.*;
import org.apache.log4j.Logger;
import com.revature.dao.*;
import com.revature.models.*;


public class UserServiceImpl implements UserService{
	private UserDAO udao;
	private static Logger log = Logger.getLogger(UserServiceImpl.class);
	
	public UserServiceImpl(UserDAOImpl dao) {
		super();
		this.udao = dao;
	}

	@Override
	public User login(String username, String password) {
		System.out.println("In service layer. Logging in user with credentials: " + username + ", " + password);
		Optional<User> users = udao.selectAll() // when I call stream()
				.stream()
				.filter(u -> (u.getUsername().equals(username) && u.getPassword().equals(password)))
				.findFirst(); // FindAny() is another option
		
		return (users.isPresent() ? users.get() : null);
	}

	@Override
	public int register(User user) {
		log.info("In service layer. Registering user: " + user);
		return udao.insert(user);
	}

	@Override
	public User findUserById(int id) {
		System.out.println("In service layer. Finding user by id num: " + id);
		return udao.selectById(id);
	}

	@Override
	public User findUserByName(String name) {
		System.out.println("In service layer. Finding user by name: " + name);
		return udao.selectByName(name);
	}

	@Override
	public List<User> findAllUsers() {
		System.out.println("In service layer. Finding all users...");
		return udao.selectAll();
	}

	@Override
	public boolean editUser(User user) {
		System.out.println("In service layer. Edit user: " + user);
		return udao.update(user);
	}

	@Override
	public boolean deleteUser(User user) {
		System.out.println("In service layer. Removing user: " + user);
		return udao.delete(user);
	}

}
