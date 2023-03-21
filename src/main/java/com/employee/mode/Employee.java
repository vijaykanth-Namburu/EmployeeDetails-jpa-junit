package com.employee.mode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;

@Entity
@Builder
@Table(name="Employees")
public class Employee {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="first_name")
	private String firstname;
	@Column(name="last_name")
	private String lastname;
	@Column(name="age")
	private String age;
	
	public Employee() {
		super();
	}

	public Employee(long i, String firstname, String lastname, String age) {
		super();
		this.id = i;
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
}
