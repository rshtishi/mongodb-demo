package com.github.rshtishi.demo.raw;

import java.time.LocalDate;

import org.bson.Document;

public class EmployeeHelper {

	public static Employee transform(Document dbEmployee) {
		return new Employee((String) dbEmployee.get("employeeID"), (String) dbEmployee.get("firstname"),
				(String) dbEmployee.get("middlename"), (String) dbEmployee.get("lastname"),
				(Double) dbEmployee.get("salary"));
	}

	public static Document transform(Employee employee) {
		Document dbEmployee = new Document().append("employeeID", employee.getEmployeeID())
				.append("firstname", employee.getFirstname()).append("middlename", employee.getMiddlename())
				.append("lastname", employee.getLastname()).append("salary", employee.getSalary());
		return dbEmployee;
	}

}
