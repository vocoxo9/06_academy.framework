package com.kh.library.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorTest {

	private final Calculator calculator = new Calculator();
	
	@BeforeAll
	static void beforeTest() throws Exception {
		System.out.println("--- test start ---");
	}

	@AfterAll
	static void afterTest() throws Exception {
		System.out.println("--- test end ---");
	}

	@Test
	@DisplayName("add test")
	void testAdd() {
		// 2 + 5 = 7 
		assertEquals(7, calculator.add(2, 5));
	}

	@Test
	@DisplayName("subtract test")
	void testSubtract() {
		// 9 - 1 = 8
		assertEquals(8, calculator.subtract(9, 1));
	}

	@Test
	@DisplayName("multiply test")
	void testMultiply() {
		// 5 * 3 = 15
		assertEquals(15, calculator.multiply(5, 3));
	}

	@Test
	@DisplayName("divide test")
	void testDivide() {
		// 14 / 7 = 2
		assertEquals(2, calculator.divide(14, 7));
	}
	
	@Test
	@DisplayName("divide failed test")
	void testDiviedFailed() {
		// 14 / 0 ... IllegalArgumentException 발생
		assertThrows(IllegalArgumentException.class, () -> calculator.divide(14,0));
	}

}
