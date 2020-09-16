package com.github.rshtishi.demo.raw.mongotemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.rshtishi.demo.raw.Employee;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MongoDBEmployeeRepositoryTest {

	@Autowired
	private MongoTemplateEmployeeRepository employeeRepository;

	@Test
	@Order(1)
	void testFindAll() {
		// setup
		// execute
		List<Employee> employees = employeeRepository.findAll();
		// verify
		int expectedSize = 0;
		assertEquals(expectedSize, employees.size());
	}

	@Test
	@Order(2)
	void testSave() {
		// setup
		Employee employee = new Employee("1", "Rando", "", "Shtishi", 2500);
		// execute
		employeeRepository.save(employee);
		// verify
		int expectedSize = 1;
		List<Employee> employees = employeeRepository.findAll();
		assertEquals(expectedSize, employees.size());
	}

	@Test
	@Order(3)
	void testFindById() {
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
		long total = employeeRepository.count();
		// verify
		long expectedTotal = 1L;
		assertEquals(expectedTotal, total);
	}

	@Test
	@Order(5)
	void testRemove() {
		// setup
		String employeeID = "1";
		Employee employee = employeeRepository.findByEmployeeID(employeeID);
		// execute
		employeeRepository.delete(employee);
		// verify
		int expectedSize = 0;
		List<Employee> employees = employeeRepository.findAll();
		assertEquals(expectedSize, employees.size());
	}

}
