package com.revature.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Reimbursement;
import com.revature.util.HibernateUtil;

public class ReimbursementDAOImpl implements ReimbursementDAO{

	@Override
	public int insert(Reimbursement reimbursement) {
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		int pk = (int) ses.save(reimbursement);
		tx.commit();
		
		return pk;
	}

	@Override
	public Reimbursement selectById(int id) {
		Session ses = HibernateUtil.getSession();
		Reimbursement reimbursement = (Reimbursement) ses.createNativeQuery("SELECT * FROM system_reimbursements WHERE system_reimbursement_id = " + id + "", Reimbursement.class).getSingleResult();
		return reimbursement;
	}

	@Override
	public List<Reimbursement> selectByManager(String manager) {
		Session ses = HibernateUtil.getSession();
		List<Reimbursement> reimbursementList = ses.createQuery("SELECT * FROM system_reimbursement WHERE system_reimbursement_manager = " + manager + "", Reimbursement.class).list();
		return reimbursementList;
	}

	@Override
	public List<Reimbursement> selectByStatus(String status) {
		Session ses = HibernateUtil.getSession();
		List<Reimbursement> reimbursementList = ses.createQuery("SELECT * FROM system_reimbursement WHERE system_reimbursement_status = " + status + "", Reimbursement.class).list();
		return reimbursementList;
	}

	@Override
	public List<Reimbursement> selectAll() {
		Session ses = HibernateUtil.getSession();
		List<Reimbursement> reimbursementList = ses.createQuery("SELECT * FROM system_reimbursement", Reimbursement.class).list();
		return reimbursementList;
	}

	@Override
	public boolean update(Reimbursement reimbursement) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.clear();
		ses.update(tx);
		tx.commit();
		
		return true;
	}

	@Override
	public boolean delete(Reimbursement reimbursement) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.clear();
		ses.delete(reimbursement);
		tx.commit();
		return true;
	}

}
