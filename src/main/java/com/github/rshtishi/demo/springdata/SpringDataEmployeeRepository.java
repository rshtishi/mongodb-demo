package com.github.rshtishi.demo.springdata;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.github.rshtishi.demo.raw.Employee;
import com.querydsl.core.types.Predicate;

@Repository
public interface SpringDataEmployeeRepository
		extends MongoRepository<Employee, String>, QuerydslPredicateExecutor<Employee>, EmployeeRepositoryCustom {

	public List<Employee> findAll();
	
	public List<Employee> findAll(Predicate predicate);

	public Employee findByEmployeeID(String employeeID);

}
