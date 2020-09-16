package com.github.rshtishi.demo.raw;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;

@Repository
public class MongoDBEmployeeRepository implements EmployeeRepository {

	@Autowired
	private MongoClient mongoClient;
	@Value("${spring.data.mongodb.database}")
	private String databaseName;
	@Value("${mongodb.employee.collection}")
	private String collectionName;

	@Override
	public List<Employee> findAll() {
		FindIterable<Document> documents = getCollection().find();
		List<Employee> employees = new ArrayList<>();
		for (Document document : documents) {
			Employee employee = EmployeeHelper.transform(document);
			employees.add(employee);
		}
		return employees;
	}

	@Override
	public Employee findByEmployeeID(String employeeID) {
		Bson filter = eq("employeeID",employeeID);
		Document document = getCollection().find(filter).first();
		return EmployeeHelper.transform(document);
	}

	@Override
	public long count() {
		return getCollection().countDocuments();
	}

	@Override
	public void save(Employee employee) {
		Document document = EmployeeHelper.transform(employee);
		Bson filter = eq("employeeID",employee.getEmployeeID());
		if(getCollection().findOneAndReplace(filter, document)==null) {
			getCollection().insertOne(document);
		}
	}

	@Override
	public void delete(Employee employee) {
		Bson filter = eq("employeeID",employee.getEmployeeID());
		getCollection().deleteOne(filter);
	}

	private MongoCollection<Document> getCollection() {
		return mongoClient.getDatabase(databaseName).getCollection(collectionName);
	}

}
