package at.crimsonbit.testparser.expression;

import at.crimsonbit.testparser.exceptions.IllegalQuestionFormatException;
import at.crimsonbit.testparser.parser.question.mapping.IKeyAction;

public class ExprEval implements IKeyAction {
	private String expr;

	public ExprEval(String string) {
		expr = string;
	}

	@Override
	public double compute(Object[] others) {
		int k = 0, i;
		StringBuilder sb = new StringBuilder();
		while ((i = expr.indexOf('$', k)) != -1) {
			String pre = expr.substring(k, i);
			sb.append(pre);
			int j = expr.indexOf('}', i);
			String num = expr.substring(i + 2, j);
			Object obj = others[Integer.parseInt(num)];
			if (obj instanceof IKeyAction) {
				if (obj == this) {
					throw new RuntimeException(new IllegalQuestionFormatException(
							"Expression has to evaluate itself, would overflow Stack"));
				}
				obj = ((IKeyAction) obj).compute(others);
			}
			sb.append(obj);
			k = j + 1;
		}
		return new ExprParser(sb.toString()).evaluate();
	}

}
