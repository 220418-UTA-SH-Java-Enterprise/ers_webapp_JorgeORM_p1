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
		
		log.info("insert sucessful, new employee user id is " + pk);
		return pk;
	}

	@Override
	public Manager selectById(int id) {
		log.info("searching for manager by id: " + id);
		Session ses = HibernateUtil.getSession();
		Manager manager = (Manager) ses.createNativeQuery("SELECT * FROM system_managers WHERE system_manager_id = " + id + "", Manager.class).getSingleResult();
		log.info("search complete, found: " + manager);
		return manager;
		
	}

	@Override
	public Manager selectByUsername(String username) {
		log.info("searching for manager by username: " + username);
		Session ses = HibernateUtil.getSession();
		Manager manager = (Manager) ses.createNativeQuery("SELECT * FROM system_managers WHERE system_manager_username = " + username + "", Manager.class).getSingleResult();
		log.info("search complete, found: " + manager);
		return manager;
	}

	@Override
	public List<Manager> selectAll() {
		log.info("fetching all managers");
		Session ses = HibernateUtil.getSession();
		List<Manager> managerList = ses.createQuery("SELECT * FROM system_managers", Manager.class).list();
		log.info("Employee list retrieved");
		return managerList;
	}

	@Override
	public boolean update(Manager manager) {
		log.info("Updating Manager. Manager info " + manager);
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.clear();
		ses.update(tx);
		tx.commit();
		log.info("update complete");
		return true;
	}

	@Override
	public boolean delete(Manager manager) {
		log.info("Deleting Manager. Manager info " + manager);
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.clear();
		ses.delete(manager);
		tx.commit();
		log.info("deletion complete");
		return true;
		
	}

}
