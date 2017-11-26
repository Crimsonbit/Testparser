package at.crimsonbit.testparser.parser.question.mapping;

import java.util.Random;

import at.crimsonbit.testparser.parser.question.ParameterType;

public class NumKey implements IKey<Double> {
	private final double minimum;
	private final double maximum;

	public NumKey(double d, double e) {
		super();
		this.minimum = d;
		this.maximum = e;
	}

	@Override
	public Double get(Random random) {
		return random.nextDouble() * (maximum - minimum) + minimum;
	}

	@Override
	public ParameterType getType() {
		return ParameterType.NUMBER;
	}

}
