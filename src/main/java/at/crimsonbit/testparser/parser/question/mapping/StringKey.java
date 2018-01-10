package at.crimsonbit.testparser.parser.question.mapping;

import java.util.Random;

import at.crimsonbit.testparser.api.sheetinterface.IKeyData;
import at.crimsonbit.testparser.parser.question.ParameterType;

public class StringKey implements IKey<String> {
	private String[] possible;
	private final int pos;

	public StringKey(String[] possible) {
		this(possible, -1);
	}
	public StringKey(String[] possible, int pos) {
		super();
		this.possible = possible;
		this.pos = pos;
	}

	public static StringKey create(IKeyData dto) {
		return new StringKey(dto.getValues());
	}

	@Override
	public String get() {
		return possible[pos];
	}
	
	@Override
	public IKey<String> parse(Random random) {
		int p = random.nextInt(possible.length);
		return new StringKey(possible, p);
	}

	@Override
	public ParameterType getType() {
		return ParameterType.STRING;
	}

}
