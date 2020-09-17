package com.github.rshtishi.demo.springdata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.rshtishi.demo.raw.Employee;
import com.github.rshtishi.demo.raw.QEmployee;
import com.querydsl.core.types.Predicate;

public class EmployeeRepositoryCustomImpl implements EmployeeRepositoryCustom {

	@Autowired
	private SpringDataEmployeeRepository employeeRepository;
	
	@Override
	public List<Employee> findEmployeeWithSalaryBetween(double min, double max) {
		QEmployee qEmployee = new QEmployee("employee");
		Predicate predicate = qEmployee.salary.between(min, max);
		return employeeRepository.findAll(predicate);
	}

}
