package com.github.rshtishi.demo.raw;

import java.time.LocalDate;

public class Employee {

	private String employeeID;
	private String firstname;
	private String middlename;
	private String lastname;
	private double salary;

	public Employee() {
		super();
	}

	public Employee(String employeeID, String firstname, String middlename, String lastname, double salary) {
		super();
		this.employeeID = employeeID;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.salary = salary;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

}
