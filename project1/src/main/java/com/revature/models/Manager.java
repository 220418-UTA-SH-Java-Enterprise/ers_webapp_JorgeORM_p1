package com.revature.models;

import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="system_managers")
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	
}
