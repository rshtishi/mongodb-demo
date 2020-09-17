package com.github.rshtishi.demo.springdata;

import java.util.List;

import com.github.rshtishi.demo.raw.Employee;

public interface EmployeeRepositoryCustom {
	
	public List<Employee> findEmployeeWithSalaryBetween(double min, double max);

}
