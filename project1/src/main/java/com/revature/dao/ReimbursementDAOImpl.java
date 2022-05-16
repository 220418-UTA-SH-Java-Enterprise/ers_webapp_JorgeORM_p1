package com.revature.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Reimbursement;
import com.revature.util.HibernateUtil;

public class ReimbursementDAOImpl implements ReimbursementDAO{
	private static Logger log = Logger.getLogger(ReimbursementDAOImpl.class);
	@Override
	public int insert(Reimbursement reimbursement) {
		log.info("Adding reimbursement into the database");
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		int pk = (int) ses.save(reimbursement);
		tx.commit();
		
		log.info("insert sucessfull, new reimbursement id is " + pk);
		return pk;
	}

	@Override
	public Reimbursement selectById(int id) {
		log.info("searching for reimbursement by id: " + id);
		Session ses = HibernateUtil.getSession();
		Reimbursement reimbursement = (Reimbursement) ses.createNativeQuery("SELECT * FROM system_reimbursements WHERE system_reimbursement_id = " + id + "", Reimbursement.class).getSingleResult();
		log.info("search complete, found: " + reimbursement);
		return reimbursement;
	}

	@Override
	public List<Reimbursement> selectByManager(String manager) {
		log.info("searching for reimbursement by manager: " + manager);
		Session ses = HibernateUtil.getSession();
		List<Reimbursement> reimbursementList = ses.createQuery("SELECT * FROM system_reimbursement WHERE system_reimbursement_manager = " + manager + "", Reimbursement.class).list();
		log.info("search complete");
		return reimbursementList;
	}

	@Override
	public List<Reimbursement> selectByStatus(String status) {
		log.info("searching for reimbursement by status: " + status);
		Session ses = HibernateUtil.getSession();
		List<Reimbursement> reimbursementList = ses.createQuery("SELECT * FROM system_reimbursement WHERE system_reimbursement_status = " + status + "", Reimbursement.class).list();
		//log.info("search complete");
		return reimbursementList;
	}

	@Override
	public List<Reimbursement> selectAll() {
		log.info("fetching all reimbursements");
		Session ses = HibernateUtil.getSession();
		List<Reimbursement> reimbursementList = ses.createQuery("SELECT * FROM system_reimbursement", Reimbursement.class).list();
		log.info("search complete");
		return reimbursementList;
	}

	@Override
	public boolean update(Reimbursement reimbursement) {
		log.info("updating reimbursement. reimbursement info " + reimbursement);
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.clear();
		ses.update(tx);
		tx.commit();
		log.info("updating completet");
		return true;
	}

	@Override
	public boolean delete(Reimbursement reimbursement) {
		log.info("deleting reimbursement. reimbursement info: " + reimbursement);
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.clear();
		ses.delete(reimbursement);
		tx.commit();
		log.info("deletion complete");
		return true;
	}

}
