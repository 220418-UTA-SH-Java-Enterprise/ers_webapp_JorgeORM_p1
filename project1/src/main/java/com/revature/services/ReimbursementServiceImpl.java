package com.revature.services;

import java.util.List;

import com.revature.dao.ReimbursementDAO;
import com.revature.dao.ReimbursementDAOImpl;
import com.revature.models.Reimbursement;

public class ReimbursementServiceImpl implements ReimbursementService{
	private ReimbursementDAO rdao;
	
	public ReimbursementServiceImpl(ReimbursementDAOImpl dao) {
		super();
		this.rdao = dao;
	}

	@Override
	public int create(Reimbursement reimbursement) {
		return rdao.insert(reimbursement);

	}

	@Override
	public Reimbursement findReimbursementById(int id) {
		return rdao.selectById(id);
	}

	@Override
	public List<Reimbursement> findReimbursementByManager(String manager) {
		return rdao.selectByManager(manager);
	}

	@Override
	public List<Reimbursement> findReimbursementByStatus(String status) {
		return rdao.selectByStatus(status);
	}

	@Override
	public List<Reimbursement> findAllReimbursements() {
		return rdao.selectAll();
	}

	@Override
	public boolean editReimbursement(Reimbursement reimbursement) {
		return rdao.update(reimbursement);
	}

	@Override
	public boolean deleteReimbursement(Reimbursement reimbursement) {
		return rdao.delete(reimbursement);
	}
	
}