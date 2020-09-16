package com.github.rshtishi.demo.raw.mongotemplate;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.github.rshtishi.demo.raw.Employee;
import com.github.rshtishi.demo.raw.EmployeeRepository;

@Repository
public class MongoTemplateEmployeeRepository implements EmployeeRepository {

	@Autowired
	private MongoTemplate mongoTemplate;
	@Value("${mongodb.employee.collection}")
	private String collectionName;

	@Override
	public List<Employee> findAll() {
		return mongoTemplate.findAll(Employee.class, collectionName);
	}

	@Override
	public Employee findByEmployeeID(String employeeID) {
		return mongoTemplate.findOne(new Query(where("employeeID").is(employeeID)), Employee.class);
	}

	@Override
	public long count() {
		return mongoTemplate.count(new Query(), collectionName);
	}

	@Override
	public void save(Employee employee) {
		mongoTemplate.save(employee);

	}

	@Override
	public void delete(Employee employee) {
		mongoTemplate.remove(employee);
	}

}
