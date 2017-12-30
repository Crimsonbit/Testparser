package at.crimsonbit.testparser.parser.question.mapping;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestDoubleKey {

	@Test
	void correctStringRepresentation() {
		DoubleKey k1 = new DoubleKey(10, 20, 1);
		DoubleKey k2 = new DoubleKey(3.55, 7.987, 3);
		assertEquals("10,0 to 20,0", k1.toString());
		assertEquals("3,550 to 7,987", k2.toString());
	}

}
