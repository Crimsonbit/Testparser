package at.crimsonbit.testparser.parser.question.mapping;

import java.util.Random;

import at.crimsonbit.testparser.api.sheetinterface.IKeyData;
import at.crimsonbit.testparser.expression.ExprEval;
import at.crimsonbit.testparser.parser.question.ParameterType;

public class ExprKey implements IKey<ExprEval> {
	private String s;
	public ExprKey(String v) {
		s = v;
	}
	
	public static ExprKey create(IKeyData data) {
		return new ExprKey(data.getExpr());
	}

	@Override
	public ExprEval get(Random random) {
		return new ExprEval(s);
	}

	@Override
	public ParameterType getType() {
		return ParameterType.EXPRESSION;
	}
}
