# mongodb-demo

A practical guide for accessing the MongoDB database.

## Topics

MongoDB is a NoSQL database that stores the information in binary JSON documents. Relational databases offer consistency and availability but unfortunately, they fall 
short on partition tolerance. MongoDB offers consistency and partition tolerance but fails to offer availability. In this demo, we are going to cover the following
topics:

- accessing MongoDB using MongoClient
- accessing MongoDB using MongoTemplate
- accessing MongoDB using Spring Data
- accessing MongoDB using Spring Data Reactive
- using QueryDSL to write type-safe queries in java

## Accessing MongoDB using MongoClient

Accessing the MongoDB with MongoClient comes with the chore of transforming the domain model to Document and vice versa. Below is an example of accessing a collection 
with MongoClient:

```
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
```


## Accessing MongoDB using MongoTemplate

Accessing the MongoDB with MongoTemplate it makes it much easier for implementing the database operations because it has convenience methods for almost
every operation: save, update, and delete. There is no more need to manually transform the domain model to document and vice versa. The conversion is done 
automatically by MongoConverters. Below is an example:

```
@Repository
public class MongoTemplateEmployeeRepository implements EmployeeRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public List<Employee> findAll() {
		return mongoTemplate.findAll(Employee.class);
	}

	@Override
	public Employee findByEmployeeID(String employeeID) {
		return mongoTemplate.findOne(new Query(where("employeeID").is(employeeID)), Employee.class);
	}

	@Override
	public long count() {
		return mongoTemplate.count(new Query(), Employee.class);
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
```

## Accessing MongoDB using Spring Data

Although the code has been reduced a lot in that there is no more mapping from and to MongoDB classes and no more collection names passing around, it can 
still be reduced even further. With Spring Data Mongo we don't need to implement the CRUD methods, they are generated default. Also, we can generate custom methods by
declaring methods with names that follow the convention of Spring Data. Below is an example:

```
@Repository
public interface SpringDataEmployeeRepository
		extends MongoRepository<Employee, String>, QuerydslPredicateExecutor<Employee>, EmployeeRepositoryCustom {

	public List<Employee> findAll();
	
	public List<Employee> findAll(Predicate predicate);

	public Employee findByEmployeeID(String employeeID);

}
```

## Accessing MongoDB using Spring Data Reactive

Instead of creating a traditional MongoDB repository, it is possible to create a reactive repository, which isdone by extending the ReactiveMongoRepository class 
or one of the other reactive repository interfaces). This will change the return types for methods that return a single value into Mono<T> (or Mono<Void> for
non returning methods) and Flux<T> for zero or more elements. The following examples demonstrates the access to MongoDB with reactive repositories:

```
@Repository
public interface ReactiveEmployeeRepository extends ReactiveMongoRepository<Employee, String> {
	
	Mono<Employee> findByEmployeeID(String employeeID);

}

```

## Using QueryDSL to write type-safe queries in java

QueryDSL it makes writing queries much easier and safer. Below is a simple example using QueryDSL:

```
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

```


