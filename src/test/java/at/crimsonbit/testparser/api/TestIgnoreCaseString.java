package at.crimsonbit.testparser.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class TestIgnoreCaseString {

	@Test
	void testEquals() {
		String s = "Hallo";
		String s2 = "HaLlO";

		IgnoreCaseString ics = new IgnoreCaseString(s);
		IgnoreCaseString ics2 = new IgnoreCaseString(s2);

		assertEquals(ics, ics2);
	}

	@Test
	void asHashMapKey() {
		Map<IgnoreCaseString, Integer> map = new HashMap<>();
		map.put(new IgnoreCaseString("all small"), 10);
		map.put(new IgnoreCaseString("all SMALL"), 35);
		assertTrue(map.containsKey(new IgnoreCaseString("AlL SmAll")));
		assertEquals(35, map.get(new IgnoreCaseString("aLl sMall")).intValue());
	}

}
