package at.crimsonbit.testparser.parser.question.mapping;

import java.util.Random;

import at.crimsonbit.testparser.api.sheetinterface.IKeyData;
import at.crimsonbit.testparser.parser.question.ParameterType;

public class IntKey implements IKey<Integer> {
	private final int minimum;
	private final int maximum;
	private final int solution;

	public IntKey(int min, int max) {
		this(min, max, 0);
	}
	
	public IntKey(int min, int max, int solution) {
		super();
		this.minimum = min;
		this.maximum = max;
		this.solution = solution;
	}

	public static IntKey create(IKeyData data) {
		return new IntKey((int) data.getMin(), (int) data.getMax());
	}

	@Override
	public Integer get() {
		return solution;
	}
	
	@Override
	public IKey<Integer> parse(Random random) {
		int sol = random.nextInt(maximum + 1 - minimum) + minimum;
		return new IntKey(this.maximum, this.maximum, sol);
	}

	@Override
	public ParameterType getType() {
		return ParameterType.INT;
	}

}
