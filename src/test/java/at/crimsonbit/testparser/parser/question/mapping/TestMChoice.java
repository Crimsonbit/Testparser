package at.crimsonbit.testparser.parser.question.mapping;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestMChoice {

	private static long seed;

	@BeforeAll
	static void initSeed() {
		seed = new Random().nextLong();
	}

	@Test
	void testNormalToString() {
		IKey<?> choice = new MChoice(new String[] { "erstes", "zweites", "drittes", "viertes" }, 0b0001);
		choice = choice.parse(new Random(seed));
		System.out.println(choice.get());
	}

	@Test
	void testSolString() {
		IKey<?> choice = new MChoice(new String[] { "erstes", "zweites", "drittes", "viertes" }, 0b0001);
		choice = choice.parse(new Random(seed));
		System.out.println(choice.getForSol());
	}

	@Test
	void testSolStringMulti() {
		IKey<?> choice = new MChoice(new String[] { "erstes", "zweites", "drittes", "viertes" }, 0b0101);
		choice = choice.parse(new Random(seed));
		System.out.println(choice.getForSol());
	}

}
