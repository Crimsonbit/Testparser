package at.crimsonbit.testparser.expression;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestMathParser {

	@Test
	void failsOnEmptyString() {
		try {
			MathParser.calculate("");
		} catch (IllegalArgumentException e) {
			return;
		}
		fail("No IllegalArgumentException catched on passingEmptyString");
	}

	@Test
	void failsOnNull() throws Exception {
		try {
			MathParser.calculate(null);
		} catch (IllegalArgumentException e) {
			return;
		}
		fail("No IllegalArgumentException catched on passingEmptyString");
	}
	
	@Test
	void sqrtOfNegative() throws Exception {
		assertEquals(Double.NaN, MathParser.calculate("sqrt(-1)"));
	}

	@Test
	void testAddition() throws Exception {
		assertEquals(42, MathParser.calculate("30 + 2 + 10"));
	}

	@Test
	void testMultiplication() throws Exception {
		assertEquals(45, MathParser.calculate("30 * 1.5"));
	}

	@Test
	void testDivision() throws Exception {
		assertEquals(10, MathParser.calculate("30 / 3"));
	}

	@Test
	void tesPower() throws Exception {
		assertEquals(1024, MathParser.calculate("2^10"));
	}

	@Test
	void testEuler() throws Exception {
		assertEquals(Math.exp(2), MathParser.calculate("e^2"), 1e-10);
	}

	@Test
	void testSin() throws Exception {
		assertEquals(Math.sin(1), MathParser.calculate("sin(1)"), 1e-10);
	}

	@Test
	void testCos() throws Exception {
		assertEquals(Math.cos(1), MathParser.calculate("cos(1)"), 1e-10);
	}

	@Test
	void testTan() throws Exception {
		assertEquals(Math.tan(1), MathParser.calculate("tan(1)"), 1e-10);
	}

	@Test
	void testPi() throws Exception {
		assertEquals(0, MathParser.calculate("sin(PI)"), 1e-10);
	}

	@Test
	void testNested() throws Exception {
		assertEquals(1.648, MathParser.calculate("e^(sqrt(4^(-2 + 2 * 0.5)))"), 0.001);
	}

}
