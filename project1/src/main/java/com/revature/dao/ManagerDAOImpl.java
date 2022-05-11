package com.revature.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Manager;
import com.revature.util.HibernateUtil;

public class ManagerDAOImpl implements ManagerDAO {
	private static Logger log = Logger.getLogger(ManagerDAOImpl.class);

	@Override
	public int insert(Manager manager) {
		
		log.info("Adding manager into the database");
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		int pk = (int) ses.save(manager);
		tx.commit();
		
		return pk;
	}

	@Override
	public Manager selectById(int id) {
		Session ses = HibernateUtil.getSession();
		Manager manager = (Manager) ses.createNativeQuery("SELECT * FROM system_managers WHERE system_manager_id = " + id + "", Manager.class).getSingleResult();
		return manager;
	}

	@Override
	public Manager selectByUsername(String username) {
		Session ses = HibernateUtil.getSession();
		Manager manager = (Manager) ses.createNativeQuery("SELECT * FROM system_managers WHERE system_manager_username = " + username + "", Manager.class).getSingleResult();
		return manager;
	}

	@Override
	public List<Manager> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Manager manager) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Manager manager) {
		// TODO Auto-generated method stub
		return false;
	}

}
