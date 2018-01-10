package at.crimsonbit.testparser.parser.question.mapping;

import java.util.Random;

import at.crimsonbit.testparser.api.sheetinterface.IKeyData;
import at.crimsonbit.testparser.parser.question.ParameterType;

public class DoubleKey implements IKey<Double> {
	private final double minimum;
	private final double maximum;
	private final int digits;
	private final double solution;

	public DoubleKey(double min, double max, int digits) {
		this(min, max, digits, 0);
	}
	
	public DoubleKey(double min, double max, int digits, double solution) {
		super();
		assert digits >= 0;
		assert max > min;
		this.minimum = min;
		this.maximum = max;
		if(digits == 0) {
			digits = 3;
		}
		this.digits = digits;
		this.solution = solution;
	}

	public static DoubleKey create(IKeyData data) {
		return new DoubleKey(data.getMin(), data.getMax(), data.getDigits());

	}
	
	@Override
	public IKey<Double> parse(Random random) {
		double d = random.nextDouble() * (maximum - minimum) + minimum;
		double rounder = Math.pow(10, digits);
		double solution =  Math.round(d * rounder)/rounder;
		return new DoubleKey(this.minimum, this.maximum, this.digits, solution);
	}

	@Override
	public Double get() {
		return solution;
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
