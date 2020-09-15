package com.github.rshtishi.demo.raw;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MongoDBEmployeeRepositoryTest {

	@Autowired
	private MongoDBEmployeeRepository employeeRepository;

	@Test
	@Order(1)
	void testFindAll() {
		// setup
		// execute
		List<Employee> employees = employeeRepository.findAll();
		// verify
		assertTrue(employees.isEmpty());
	}

	@Test
	@Order(2)
	void testSave() {
		// setup
		Employee employee = new Employee("1", "Rando", "", "Shtishi", 2500);
		// execute
		employeeRepository.save(employee);
		// verify
		List<Employee> employees = employeeRepository.findAll();
		int expectedSize = 1;
		assertEquals(expectedSize, employees.size());
	}

	@Test
	@Order(3)
	void testFindByEmployeeId() {
		// setup
		String employeeID = "1";
		// execute
		Employee employee = employeeRepository.findByEmployeeID(employeeID);
		// verify
		String expectedName = "Rando";
		assertEquals(expectedName, employee.getFirstname());
	}

	@Test
	@Order(4)
	void testCount() {
		// setup
		// execute
		long noDocs = employeeRepository.count();
		// verify
		long expectedNo = 1L;
		assertEquals(expectedNo, noDocs);
	}

	@Test
	@Order(5)
	void testDelete() {
		// setup
		String employeeID = "1";
		Employee employee = employeeRepository.findByEmployeeID(employeeID);
		// execute
		employeeRepository.delete(employee);
		// verify
		List<Employee> employees = employeeRepository.findAll();
		int expectedSize = 0;
		assertEquals(expectedSize, employees.size());
	}

}
