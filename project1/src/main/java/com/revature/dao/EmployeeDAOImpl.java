package com.revature.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Employee;
import com.revature.util.HibernateUtil;

public class EmployeeDAOImpl implements EmployeeDAO{
	private static Logger log = Logger.getLogger(EmployeeDAOImpl.class);

	@Override
	public int insert(Employee employee) {
		log.info("adding Employee to database. Employee info" + employee);
		
		Session ses= HibernateUtil.getSession();
		
		Transaction tx = ses.beginTransaction();
		
		int pk = (int) ses.save(employee);
		
		tx.commit();
		
		log.info("insert successful! New Employee User id is" + pk);
		return pk;
	}

	@Override
	public Employee selectById(int id) {
		log.info("searching Employee by id: " + id);
		
		Session ses = HibernateUtil.getSession();
		
		Employee user = (Employee) ses.createNativeQuery("SELECT * FROM employee WHERE employee_id = " + id + "", Employee.class).getSingleResult();
		
		log.info("Search complete! Found: " + user);
		return user;
	}

	@Override
	public Employee selectByFirstName(String firstName) {
log.info("searching Employee by firstName: " + firstName);
		
		Session ses = HibernateUtil.getSession();
		
		Employee user = (Employee) ses.createNativeQuery("SELECT * FROM employee WHERE employee_firstname = " + firstName + "", Employee.class).getSingleResult();
		
		log.info("Search complete! Found: " + user);
		return user;

	}

	@Override
	public List<Employee> selectAll() {
		log.info("getting all Employee users from database...");
		
		Session ses = HibernateUtil.getSession();
		List<Employee> userList = ses.createQuery("from Employee", Employee.class).list();
		log.info("Employee list retrieved! Size: " +userList.size());
		return userList;
	}

	@Override
	public boolean update(Employee employee) {
		log.info("Updating Employee. Employee info " + employee);
		
		Session ses = HibernateUtil.getSession();
		
		Transaction tx = ses.beginTransaction();
		
		ses.clear();
		
		ses.update(employee);
		
		tx.commit();
		
		log.info("update complete");
		return true;
	}

	@Override
	public boolean delete(Employee user) {
log.info("Deleteting Employee. Employee info " + user);
		
		Session ses = HibernateUtil.getSession();
		
		Transaction tx = ses.beginTransaction();
		
		ses.clear();
		
		ses.delete(user);
		
		tx.commit();
		
		log.info("deletion complete");
		
		return true;
	}

}
