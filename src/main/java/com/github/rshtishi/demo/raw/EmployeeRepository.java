package com.github.rshtishi.demo.raw;

import java.util.List;

public interface EmployeeRepository {
	
	List<Employee> findAll();
	Employee findByEmployeeID(String employeeID);
	long count();
	void save(Employee employee);
	void delete(Employee employee);

	

}
