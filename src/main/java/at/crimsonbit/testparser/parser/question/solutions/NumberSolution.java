package at.crimsonbit.testparser.parser.question.solutions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import at.crimsonbit.testparser.exceptions.IllegalQuestionFormatException;
import at.crimsonbit.testparser.parser.question.EnumTaskType;
import at.crimsonbit.testparser.parser.question.mapping.QMap;

public class NumberSolution extends Solution<Double> {

	private static final EnumTaskType type = EnumTaskType.NUMBER;
	private static Pattern simpleRegex = null;
	private static Pattern complicatedRegex = null;
	private double solution;

	private double epsilon;

	public NumberSolution(String task) {
		super(task.trim());
	}

	@Override
	public NumberSolution parse(QMap map) throws IllegalQuestionFormatException {
		if (simpleRegex == null) {
			simpleRegex = Pattern.compile("\\$\\{[0-9]+\\}");
		}
		Matcher match = simpleRegex.matcher(task);
		if (!match.matches()) {
			if (!tryParseComplicated(map))
				throw new IllegalQuestionFormatException("Illegal Format for Number Solution: " + task);
		} else {
			epsilon = 0.001;
		}
		String numString = task.substring(2, task.indexOf('}'));
		Object mapVal = map.get(Integer.parseInt(numString));
		if (mapVal instanceof Number) {
			solution = ((Number) mapVal).doubleValue();
		}

		return this;
	}

	private boolean tryParseComplicated(QMap map) {
		if (complicatedRegex == null) {
			complicatedRegex = Pattern.compile("\\+- *[0-9]+\\.?[0-9]*");
		}
		String tmp = simpleRegex.matcher(task).replaceAll("").trim();
		if (!complicatedRegex.matcher(tmp).matches())
			return false;
		tmp = tmp.substring(2);
		epsilon = Double.parseDouble(tmp);
		return true;
	}

	@Override
	public Double evaluate() {
		return solution;
	}

	public EnumTaskType getType() {
		return type;
	}

	@Override
	public String toString() {
		int digits = (int) Math.log10(1 / epsilon);
		return String.format("%." + digits + "f", solution);
	}

	@Override
	public boolean isSolution(Object obj) {
		if (!(obj instanceof Number)) {
			try {
				obj = Double.parseDouble(obj.toString());
			} catch (NumberFormatException e) {
				return false;
			}
		}
		return Math.abs(solution - ((Number) obj).doubleValue()) < epsilon;

	}

}
