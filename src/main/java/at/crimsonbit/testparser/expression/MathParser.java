package at.crimsonbit.testparser.expression;

import java.util.ArrayList;
import java.util.List;

/**
 * An advanced numerical mathematical expression parser
 * 
 * @author Clemens Lechner
 *
 */
class MathParser {
	/*
	 * private static final Map<String, Consumer<List<String>>> actionMap = new
	 * LinkedHashMap<String, Consumer<List<String>>>();
	 * 
	 * static { actionMap.put("(", MathParser::handleParentheses);
	 * actionMap.put("^", MathParser::handlePow); }
	 * 
	 * private static void handleParentheses(List<String> input) { int i =
	 * input.indexOf("("); int indexEnd = input.lastIndexOf(")");
	 * 
	 * double result = calculatex(input.subList(i, indexEnd)); input.set(i,
	 * Double.toString(result)); for (int j = i + 1; j <= indexEnd; j++) {
	 * input.remove(j); } }
	 * 
	 * private static void handlePow(List<String> input) { int i =
	 * input.indexOf("^"); double d = Math.pow(Double.parseDouble(input.get(i - 1)),
	 * Double.parseDouble(input.get(i + 1))); input.set(i, Double.toString(d));
	 * input.remove(i + 1); input.remove(i - 1); }
	 * 
	 */
	public static double calculate(String input) {
		ArrayList<String> in = compile(input);
		return calculatex(in);

	}

	private static double calculatex(List<String> input) {
		while (input.size() > 1) {

			if (input.contains("(")) {
				int i = input.indexOf("(");
				int indexEnd = input.lastIndexOf(")");

				double result = calculatex(input.subList(i + 1, indexEnd));
				input.set(i, Double.toString(result));
				indexEnd = input.lastIndexOf(")");
				for (int j = indexEnd; j > i; j--) {
					input.remove(j);
				}
			} else if (input.contains("^")) {
				int i = input.indexOf("^");
				double d = Math.pow(Double.parseDouble(input.get(i - 1)), Double.parseDouble(input.get(i + 1)));
				input.set(i, Double.toString(d));
				input.remove(i + 1);
				input.remove(i - 1);
			} else if (input.contains("sin")) {
				int i = input.indexOf("sin");
				double d = Math.sin(Double.parseDouble(input.get(i + 1)));
				input.set(i, Double.toString(d));
				input.remove(i + 1);
			} else if (input.contains("cos")) {
				int i = input.indexOf("cos");
				double d = Math.cos(Double.parseDouble(input.get(i + 1)));
				input.set(i, Double.toString(d));
				input.remove(i + 1);
			} else if (input.contains("sqrt")) {
				int i = input.indexOf("sqrt");
				double d = Math.sqrt(Double.parseDouble(input.get(i + 1)));
				input.set(i, Double.toString(d));
				input.remove(i + 1);
			} else if (input.contains("*")) {
				int i = input.indexOf("*");
				double d = Double.parseDouble(input.get(i - 1)) * Double.parseDouble(input.get(i + 1));
				input.set(i, Double.toString(d));
				input.remove(i + 1);
				input.remove(i - 1);
			} else if (input.contains("/")) {
				int i = input.indexOf("/");
				double d = Double.parseDouble(input.get(i - 1)) / Double.parseDouble(input.get(i + 1));
				input.set(i, Double.toString(d));
				input.remove(i + 1);
				input.remove(i - 1);
			} else if (input.contains("+")) {
				int i = input.indexOf("+");
				double d = Double.parseDouble(input.get(i - 1)) + Double.parseDouble(input.get(i + 1));
				input.set(i, Double.toString(d));
				input.remove(i + 1);
				input.remove(i - 1);
			} else if (input.contains("-")) {
				int i = input.indexOf("-");
				double d = Double.parseDouble(input.get(i - 1)) - Double.parseDouble(input.get(i + 1));
				input.set(i, Double.toString(d));
				input.remove(i + 1);
				input.remove(i - 1);
			}

		}
		return Double.parseDouble(input.get(0));
	}

	private static ArrayList<String> compile(String input) {

		// remove white space, assume only spaces or tabs
		/*
		 * StringBuilder b = new StringBuilder(); StringTokenizer t = new
		 * StringTokenizer(input, " "); while (t.hasMoreElements())
		 * b.append(t.nextToken()); t = new StringTokenizer(b.toString(), "\t"); b = new
		 * StringBuilder(); while (t.hasMoreElements()) b.append(t.nextToken());
		 * 
		 * input = b.toString();
		 */

		ArrayList<String> out = new ArrayList<String>();

		input = input.replaceAll("e", Double.toString(Math.E));
		input = input.replaceAll("PI", Double.toString(Math.PI));

		String s = "";
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c >= '0' && c <= '9' || c == '.') {
				s += c;
			} else if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
				s += c;
			} else if (c != ' ') {
				if (s.equals("") && c == '-') {
					s += c;
				} else {
					if (!s.equals("")) {
						out.add(s);
						s = "";
					}
					out.add(Character.toString(c));
				}
			}
		}
		if (!"".equals(s)) {
			out.add(s);
		}

		return out;
	}

}
