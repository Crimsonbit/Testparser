package at.crimsonbit.testparser.parser.question.mapping;

import java.util.Random;

import at.crimsonbit.testparser.parser.dto.KeyDTO;
import at.crimsonbit.testparser.parser.question.ParameterType;

public class IntKey implements IKey<Integer> {
	private final int minimum;
	private final int maximum;

	public IntKey(int min, int max) {
		super();
		this.minimum = min;
		this.maximum = max;
	}

	public static IntKey create(KeyDTO data) {
		return new IntKey((int) data.getMinimum(), (int) data.getMaximum());
	}

	@Override
	public Integer get(Random random) {
		return random.nextInt(maximum + 1 - minimum) + minimum;
	}

	@Override
	public ParameterType getType() {
		return ParameterType.INT;
	}

}
