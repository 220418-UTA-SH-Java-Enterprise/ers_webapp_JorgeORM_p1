package com.revature.services;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementService {
	public int create(Reimbursement reimbursement);
	public Reimbursement findReimbursementById(int id);
	public List<Reimbursement> findReimbursementByManager(String manager);
	public List<Reimbursement> findReimbursementByStatus(String status);
	public List<Reimbursement> findAllReimbursements();
	public boolean editReimbursement(Reimbursement reimbursement);
	public boolean deleteReimbursement(Reimbursement reimbursement);
	
	
}
