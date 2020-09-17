package com.github.rshtishi.demo.reactive;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.rshtishi.demo.raw.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ReactiveEmployeeRepositoryTest {

	@Autowired
	private ReactiveEmployeeRepository employeeRepository;

	@Test
	@Order(1)
	void testFindAll() {
		// setup
		// execute
		Flux<Employee> employeeFlux = employeeRepository.findAll();
		// verify
		StepVerifier.create(employeeFlux).expectNextCount(0).verifyComplete();
	}

	@Test
	@Order(2)
	void testSave() {
		// setup
		Employee employee = new Employee("1", "Rando", "", "Shtishi", 2500);
		// execute
		Mono<Employee> employeeMono = employeeRepository.save(employee);
		// verify
		StepVerifier.create(employeeMono).expectNext(employee).verifyComplete();

	}

	@Test
	@Order(3)
	void testFindByEmployeID() {
		// setup
		String employeeID = "1";
		// execute
		Mono<Employee> employeeMono = employeeRepository.findByEmployeeID(employeeID);
		// verify
		StepVerifier.create(employeeMono).expectNextMatches(e -> e.getEmployeeID().equals(employeeID)).verifyComplete();
	}

	@Test
	@Order(4)
	void testCount() {
		// setup
		// execute
		Mono<Long> totalMono = employeeRepository.count();
		// verify
		long expectedTotal = 1L;
		StepVerifier.create(totalMono).expectNext(expectedTotal).verifyComplete();
	}

	@Test
	@Order(5)
	void testRemove() {
		//setup
		String employeeID = "1";
		Employee employee= employeeRepository.findByEmployeeID(employeeID).block();
		//execute
		employeeRepository.delete(employee).block();
		//verify
		Flux<Employee> employeeFlux = employeeRepository.findAll();
		StepVerifier.create(employeeFlux).expectNextCount(0).verifyComplete();
	}
}
