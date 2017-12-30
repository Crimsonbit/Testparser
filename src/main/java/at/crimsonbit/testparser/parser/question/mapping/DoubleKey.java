package at.crimsonbit.testparser.parser.question.mapping;

import java.util.Random;

import at.crimsonbit.testparser.api.sheetinterface.IKeyData;
import at.crimsonbit.testparser.parser.question.ParameterType;

public class DoubleKey implements IKey<Double> {
	private final double minimum;
	private final double maximum;
	private final int digits;

	public DoubleKey(double min, double max, int digits) {
		super();
		assert digits >= 0;
		assert max > min;
		this.minimum = min;
		this.maximum = max;
		if(digits == 0) {
			digits = 3;
		}
		this.digits = digits;
	}

	public static DoubleKey create(IKeyData data) {
		return new DoubleKey(data.getMin(), data.getMax(), data.getDigits());

	}

	@Override
	public Double get(Random random) {
		double d = random.nextDouble() * (maximum - minimum) + minimum;
		double rounder = Math.pow(10, digits);
		return Math.round(d * rounder)/rounder;
	}

	@Override
	public ParameterType getType() {
		return ParameterType.DOUBLE;
	}
	
	@Override
	public String toString() {
		return String.format("%." + digits + "f to %." + digits + "f", minimum, maximum);
	}

}
