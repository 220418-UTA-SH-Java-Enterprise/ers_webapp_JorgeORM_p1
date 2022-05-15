package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	
	static Configuration cfg = new Configuration().configure("hibernate.cfg.xml")
			.setProperty("hibernate.connection.url", System.getenv("db_url"))
			.setProperty("hibernate.connection.username", System.getenv("db_username"))
			.setProperty("hibernate.connection.password", System.getenv("db_password"));

	private static SessionFactory sf = cfg.buildSessionFactory();
	
	//private static SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

	private static Session ses;
	
	public static Session getSession() {
		if(ses == null) {
			ses = sf.openSession(); //this returns a JDBC connection
		}
		
		return ses;
	}
	
	public static void closeSes() {
		ses.close();
		sf.close();
	}
}