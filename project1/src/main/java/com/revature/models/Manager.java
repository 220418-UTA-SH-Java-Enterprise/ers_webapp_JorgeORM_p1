package com.revature.models;

import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="system_managers")
@Getter
@Setter
@EqualsAndHashCode
@ToString

public class Manager {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="system_manager_id")
	private int id;
	
	@Column(name="system_manager_username")
	private String username;
	
	@Column(name="system_manager_password")
	private String password;
	
	@Column(name="system_manager_email")
	private String email;
	
	@Column(name="system_manager_firstname")
	private String firstName;
	
	@Column(name="system_manager_lastname")
	private String lastName;

	public Manager() {
		super();
	}

	public Manager(String username, String password, String email, String firstName, String lastName) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Manager(int id, String username, String password, String email, String firstName, String lastName) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	
	
}
