package at.crimsonbit.testparser.parser.question;

import java.util.Random;

import at.crimsonbit.testparser.parser.question.mapping.QMap;

public class Question {
	private QMap map;
	private AbstractQuestion question;

	Question(AbstractQuestion q, Random r) {
		this.question = q;
		map = new QMap(question.keys);
		map.calculate(r);
	}

	@Override
	public String toString() {
		return getTasks();
	}

	public String getTasks() {
		StringBuilder sb = new StringBuilder();
		for (Task t : question.tasks) {
			sb.append(t.getText(map));
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

	public String getTask(int n) {
		return question.tasks[n].getText(map);
	}

	public String getSolutions() {
		StringBuilder sb = new StringBuilder();
		for (Task t : question.solve) {
			sb.append(t.getText(map));
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

	public boolean isSolution(Object obj, int task) {
		Object solution = question.solve[task].evaluate(map);
		if (solution instanceof Number && obj instanceof Number) {
			Number num = (Number) obj;
			Number nsol = (Number) solution;
			return epsilonEquals(num, nsol);
		} else if (solution instanceof String && obj instanceof String) {
			String s1 = (String) obj;
			String s2 = (String) solution;
			return s1.equalsIgnoreCase(s2);
		}

		else {
			return solution.equals(obj);
		}
	}

	public String getSolution(int n) {
		return question.solve[n].getText(map);
	}

	/**
	 * Checks if n1 and n2 are equal. It checks for 2 cases, if both Numbers are
	 * Integers, then they are checked to equality, if at least one of them is not,
	 * they are interpreted as doubles and checked to an accuracy of 3 digits behind
	 * the comma
	 * 
	 * @param n1
	 * @param n2
	 * @return
	 */
	private boolean epsilonEquals(Number n1, Number n2) {

		if (n1.getClass() == Integer.class && n2.getClass() == Integer.class) {
			return n1.intValue() == n2.intValue();
		} else {
			double d1 = n1.doubleValue();
			double d2 = n2.doubleValue();
			double epsilon = Math.abs(d1 - d2);
			return epsilon <= 0.001;
		}
	}

}
