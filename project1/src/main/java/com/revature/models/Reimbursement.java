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
	private float amount;
	@Column(name="system_reimbursement_status")
	private String status;
	@Column(name="system_reimbursement_statuscode")
	private int statusCode;
	@Column(name="system_reimbursement_manager")
	private String manager;
	@Column(name="system_reimbursement_managerid")
	private int managerId;
	@Column(name="system_reimbursement_employee")
	private String employee;
	@Column(name="system_reimbursement_employeeid")
	private int employeeId;
	
	public Reimbursement() {
		super();
	}

	public Reimbursement(float amount, String status, int statusCode, String manager, int managerId, String employee,
			int employeeId) {
		super();
		this.amount = amount;
		this.status = status;
		this.statusCode = statusCode;
		this.manager = manager;
		this.managerId = managerId;
		this.employee = employee;
		this.employeeId = employeeId;
	}

	public Reimbursement(int id, float amount, String status, int statusCode, String manager, int managerId,
			String employee, int employeeId) {
		super();
		this.id = id;
		this.amount = amount;
		this.status = status;
		this.statusCode = statusCode;
		this.manager = manager;
		this.managerId = managerId;
		this.employee = employee;
		this.employeeId = employeeId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
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

	public String getEmployee() {
		return employee;
	}

	public void setEmployee(String employee) {
		this.employee = employee;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	

	
	

}
