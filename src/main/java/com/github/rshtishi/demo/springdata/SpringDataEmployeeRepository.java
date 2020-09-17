package com.github.rshtishi.demo.springdata;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.github.rshtishi.demo.raw.Employee;

@Repository
public interface SpringDataEmployeeRepository extends MongoRepository<Employee, String>{
	
	public List<Employee> findAll();
	
	public Employee findByEmployeeID(String employeeID);

}
