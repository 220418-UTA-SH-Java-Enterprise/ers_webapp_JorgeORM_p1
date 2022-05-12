package com.revature.models;

import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="system_reimbursements")
@EqualsAndHashCode
@ToString

public class Reimbursement {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="system_reimbursement_id")
	private int id;
	@Column(name="system_reimbursement_amount")
	private int amount;
	@Column(name="system_reimbursement_status")
	private String status;
	@Column(name="system_reimbursement_statuscode")
	private int statusCode;
	@Column(name="system_reimbursement_manager")
	private String manager;
	@Column(name="system_reimbursement_managerid")
	private int managerId;
	
	public Reimbursement() {
		super();
	}

	public Reimbursement(int amount, String status, int statusCode, String manager, int managerId) {
		super();
		this.amount = amount;
		this.status = status;
		this.statusCode = statusCode;
		this.manager = manager;
		this.managerId = managerId;
	}

	public Reimbursement(int id, int amount, String status, int statusCode, String manager, int managerId) {
		super();
		this.id = id;
		this.amount = amount;
		this.status = status;
		this.statusCode = statusCode;
		this.manager = manager;
		this.managerId = managerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	
	

}
