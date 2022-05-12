package com.revature.dao;
import java.util.List;
import com.revature.models.Reimbursement;

public interface ReimbursementDAO {
	public int insert(Reimbursement reimbursement);
	
	public Reimbursement selectById(int id);
	
	public List<Reimbursement> selectByManager(String manager);
	
	public List<Reimbursement> selectByStatus(String status);
	
	public List<Reimbursement> selectAll();
	
	public boolean update(Reimbursement reimbursement);
	
	public boolean delete(Reimbursement reimbursement);
	

}
