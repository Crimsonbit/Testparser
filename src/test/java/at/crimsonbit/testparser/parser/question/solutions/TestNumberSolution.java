package at.crimsonbit.testparser.parser.question.solutions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import at.crimsonbit.testparser.exceptions.IllegalQuestionFormatException;
import at.crimsonbit.testparser.parser.question.mapping.DoubleKey;
import at.crimsonbit.testparser.parser.question.mapping.IKey;
import at.crimsonbit.testparser.parser.question.mapping.IntKey;
import at.crimsonbit.testparser.parser.question.mapping.QMap;

class TestNumberSolution {

	@Nested
	class TestNumberSolutionPatterns {
		@Test
		void testSimplePattern() throws Exception {
			Pattern pat = NumberSolution.getSimpleRegex();
			Matcher m = pat.matcher("${12}");
			assertTrue(m.matches());
			assertEquals("12", m.group(1));
		}

		@Test
		void testEpsilonPattern() throws Exception {
			Pattern pat = NumberSolution.getEpsilonRegex();
			Matcher m = pat.matcher("${12} +- 1.05");
			assertTrue(m.matches());
			assertEquals("12", m.group(1));
			assertEquals("1.05", m.group(2));
		}
	}

	@Nested
	class TestNumberSolutionDouble {

		@Test
		void testAnswer() throws IllegalQuestionFormatException {
			NumberSolution sol = new NumberSolution("${0}");
			double realValue = parseAndGetSolution(sol);
			double solutionAnswer = sol.evaluate();
			assertEquals(realValue, solutionAnswer);
		}

		@Test
		void testString() throws IllegalQuestionFormatException {
			NumberSolution sol = new NumberSolution("${0} +- 0.0000001");
			double realValue = parseAndGetSolution(sol);
			String solutionString = sol.toString();
			solutionString = solutionString.replace(',', '.');
			double solutionAnswer = Double.parseDouble(solutionString);
			assertEquals(realValue, solutionAnswer, 0.0000001);
		}

		@Test
		void testSolve() throws IllegalQuestionFormatException {
			NumberSolution sol = new NumberSolution("${0}");
			double realValue = parseAndGetSolution(sol);
			assertTrue(sol.isSolution(realValue));
		}

		@Test
		void testSolveString() throws IllegalQuestionFormatException {
			NumberSolution sol = new NumberSolution("${0}");
			double realValue = parseAndGetSolution(sol);
			String solverVal = String.valueOf(realValue);
			assertTrue(sol.isSolution(solverVal));
		}

		private double parseAndGetSolution(NumberSolution sol) throws IllegalQuestionFormatException {
			QMap map = new QMap(new IKey[] { new DoubleKey(10.0, 20.0, 6) });
			map.calculate(new Random(0xABCDEF));
			sol.parse(map);
			double realValue = (double) map.get(0);
			return realValue;
		}

	}

	@Nested
	class TestNumberSolutionEpsilon {

		@Test
		void testFixedEpsilon() throws IllegalQuestionFormatException {
			NumberSolution sol = new NumberSolution("${0} +- 1");
			double realValue = parseAndGetSolution(sol);
			assertTrue(sol.isSolution(realValue - 1 + 1e-9));
			assertTrue(sol.isSolution(realValue + 1 - 1e-9));
		}

		@Test
		void testPercentEpsilon() throws IllegalQuestionFormatException {
			NumberSolution sol = new NumberSolution("${0} +- 1%");
			double realValue = parseAndGetSolution(sol);
			assertTrue(sol.isSolution(realValue *= 1.009999));
			assertTrue(sol.isSolution(realValue *= 0.990001));
		}

		@Test
		void testWithE() throws IllegalQuestionFormatException {
			NumberSolution sol = new NumberSolution("${0} +- 1e-3");
			double realValue = parseAndGetSolution(sol);
			assertTrue(sol.isSolution(realValue + 1e-3 - 1e-9));
			assertTrue(sol.isSolution(realValue - 1e-3 + 1e-9));
		}

		@Test
		void testSolveString() throws IllegalQuestionFormatException {
			NumberSolution sol = new NumberSolution("${0}");
			double realValue = parseAndGetSolution(sol);
			String solverVal = String.valueOf(realValue);
			assertTrue(sol.isSolution(solverVal));
		}

		private double parseAndGetSolution(NumberSolution sol) throws IllegalQuestionFormatException {
			QMap map = new QMap(new IKey[] { new DoubleKey(10.0, 20.0, 6) });
			map.calculate(new Random(0xABCDEF));
			sol.parse(map);
			double realValue = (double) map.get(0);
			return realValue;
		}

	}

	@Nested
	class TestNumberSolutionInt {

		@Test
		void testAnswer() throws IllegalQuestionFormatException {
			NumberSolution sol = new NumberSolution("${0}");
			int realValue = parseAndGetSolution(sol);
			double solutionAnswer = sol.evaluate();
			assertEquals(realValue, (int) solutionAnswer);
		}

		@Test
		void testString() throws IllegalQuestionFormatException {
			NumberSolution sol = new NumberSolution("${0}");
			int realValue = parseAndGetSolution(sol);
			String solutionString = sol.toString();
			solutionString = solutionString.replace(',', '.');
			double solutionAnswer = Double.parseDouble(solutionString);
			assertEquals(realValue, (int) solutionAnswer);
		}

		@Test
		void testSolve() throws IllegalQuestionFormatException {
			NumberSolution sol = new NumberSolution("${0}");
			int realValue = parseAndGetSolution(sol);
			assertEquals(true, sol.isSolution(realValue));
		}

		@Test
		void testSolveString() throws IllegalQuestionFormatException {
			NumberSolution sol = new NumberSolution("${0}");
			int realValue = parseAndGetSolution(sol);
			String solverVal = String.valueOf(realValue);
			assertEquals(true, sol.isSolution(solverVal));
		}

		private int parseAndGetSolution(NumberSolution sol) throws IllegalQuestionFormatException {
			QMap map = new QMap(new IKey[] { new IntKey(10, 100) });
			map.calculate(new Random(0xABCDEF));
			sol.parse(map);
			int realValue = (int) map.get(0);
			return realValue;
		}

	}

}
