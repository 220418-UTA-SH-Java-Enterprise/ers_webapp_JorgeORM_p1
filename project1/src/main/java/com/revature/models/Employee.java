package com.revature.models;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name="employee")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="employee_id")
	private int id;
	
	@Column(name="employee_username")
	private String username;
	
	@Column(name="employee_password")
	private String password;
	
	@Column(name="employee_firstname")
	private String firstName;
	
	@Column(name="employee_lastname")
	private String lastName;

	public Employee() {
		super();
	}

	public Employee(String username, String password, String firstName, String lastName) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Employee(int id, String username, String password, String firstName, String lastName) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	

}
