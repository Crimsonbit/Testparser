package at.crimsonbit.testparser.parser.question.mapping;

import java.util.Random;

import at.crimsonbit.testparser.parser.dto.KeyDTO;
import at.crimsonbit.testparser.parser.question.ParameterType;

public class DoubleKey implements IKey<Double> {
	private final double minimum;
	private final double maximum;

	public DoubleKey(double min, double max) {
		super();
		this.minimum = min;
		this.maximum = max;
	}

	public static DoubleKey create(KeyDTO data) {
		return new DoubleKey(data.getMinimum(), data.getMaximum());

	}

	@Override
	public Double get(Random random) {
		return random.nextDouble() * (maximum - minimum) + minimum;
	}

	@Override
	public ParameterType getType() {
		return ParameterType.DOUBLE;
	}

}
