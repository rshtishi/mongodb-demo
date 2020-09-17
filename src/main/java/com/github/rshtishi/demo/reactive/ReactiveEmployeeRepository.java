package com.github.rshtishi.demo.reactive;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.github.rshtishi.demo.raw.Employee;

import reactor.core.publisher.Mono;

@Repository
public interface ReactiveEmployeeRepository extends ReactiveMongoRepository<Employee, String> {
	
	Mono<Employee> findByEmployeeID(String employeeID);

}
