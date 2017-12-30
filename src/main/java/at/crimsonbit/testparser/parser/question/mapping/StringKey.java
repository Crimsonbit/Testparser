package at.crimsonbit.testparser.parser.question.mapping;

import java.util.Random;

import at.crimsonbit.testparser.api.sheetinterface.IKeyData;
import at.crimsonbit.testparser.parser.question.ParameterType;

public class StringKey implements IKey<String> {
	private String[] possible;

	public StringKey(String[] possible) {
		super();
		this.possible = possible;
	}

	public static StringKey create(IKeyData dto) {
		return new StringKey(dto.getValues());
	}

	@Override
	public String get(Random random) {
		return possible[random.nextInt(possible.length)];
	}

	@Override
	public ParameterType getType() {
		return ParameterType.STRING;
	}

}
