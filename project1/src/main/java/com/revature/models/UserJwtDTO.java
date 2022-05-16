package com.revature.models;

public class UserJwtDTO {
	private int id;
	private String username;
	private String firstName;
	private String lastName;
	
	private UserRole role;
	public UserJwtDTO() {
		super();
	}
	
	public UserJwtDTO(int id, String username, String firstName, String lastName, UserRole role) {
		super();
		this.id = id;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
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
	
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "UserJwtDTO [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName=" + lastName + ", role="+ role + "]";
	}
}