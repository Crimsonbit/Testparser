package at.crimsonbit.testparser.parser.question.solutions;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import at.crimsonbit.testparser.exceptions.IllegalQuestionFormatException;
import at.crimsonbit.testparser.parser.question.EnumTaskType;
import at.crimsonbit.testparser.parser.question.mapping.QMap;

public class NumberSolution extends Solution<Double> {

	private static final double _3_DIGIT_EPSILON = 0.001000001;// a little larger than 0.001, so that only 3 digits get
																// displayed
	private static final EnumTaskType type = EnumTaskType.NUMBER;
	private static Pattern simpleRegex = null;

	static Pattern getSimpleRegex() {
		if (simpleRegex == null) {
			simpleRegex = Pattern.compile("\\$\\{([0-9]+)\\}");
		}
		return simpleRegex;
	}

	static Pattern getEpsilonRegex() {
		if (epsilonRegex == null) {
			epsilonRegex = Pattern.compile("\\$\\{([0-9]+)\\} *\\+- *([0-9]+\\.?[0-9]*e?-?[0-9]*)%?");
		}
		return epsilonRegex;
	}

	private static Pattern epsilonRegex = null;
	private double solution = Double.NaN;

	private double epsilon;
	private boolean percent = false;

	public NumberSolution(String task) {
		super(task.trim());
	}

	@Override
	public NumberSolution parse(QMap map) throws IllegalQuestionFormatException {
		Matcher match = getSimpleRegex().matcher(task);
		if (!match.matches()) {
			if (!tryParseEpsilon(map))
				throw new IllegalQuestionFormatException("Illegal Format for Number Solution: " + task);
		} else {
			initVal(map, match.group(1));
			epsilon = _3_DIGIT_EPSILON;
		}
		return this;
	}

	private void initVal(QMap map, String index) {
		Object mapVal = map.get(Integer.parseInt(index));
		if (mapVal instanceof Number) {
			solution = ((Number) mapVal).doubleValue();
			if (percent) {
				epsilon *= solution / 100;
			}
		}
	}

	private boolean tryParseEpsilon(QMap map) {
		Matcher match = getEpsilonRegex().matcher(task);
		if (!match.matches())
			return false;
		if (task.endsWith("%")) {
			percent = true;
		}
		epsilon = Double.parseDouble(match.group(2));
		if (percent && epsilon > 100) {
			epsilon = 100;
		}
		initVal(map, match.group(1));
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
		// How many digits are needed
		int digits = (int) Math.floor(Math.log10(1 / epsilon));
		digits += 1;
		if (digits < 0)
			digits = 0;
		return String.format("%." + digits + "f", solution, Locale.ROOT);
	}

	@Override
	public boolean isSolution(Object obj) {
		// Check if already parsed, only false if solution is NaN
		assert solution == solution;

		if (!(obj instanceof Number)) {
			try {
				String s = obj.toString();
				s = s.replace(',', '.');
				obj = Double.parseDouble(s);
			} catch (NumberFormatException e) {
				return false;
			}
		}
		return Math.abs(solution - ((Number) obj).doubleValue()) < epsilon;

	}

}
