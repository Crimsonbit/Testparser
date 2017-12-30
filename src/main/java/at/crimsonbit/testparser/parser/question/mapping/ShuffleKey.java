package at.crimsonbit.testparser.parser.question.mapping;

import java.util.Random;

import at.crimsonbit.testparser.api.sheetinterface.IKeyData;
import at.crimsonbit.testparser.parser.question.ParameterType;

public class ShuffleKey implements IKey<String> {

	private String[] values;

	public static ShuffleKey create(IKeyData data) {
		return new ShuffleKey(data.getValues());
	}

	public ShuffleKey(String[] values) {
		this.values = values;
	}

	@Override
	public String get(Random random) {
		String[] sVal = values.clone();
		shuffleArray(sVal, random);
		StringBuilder sb = new StringBuilder();
		for (String s : sVal) {
			sb.append(s);
			sb.append('\n');
		}
		return sb.toString();
	}

	private static <T> void shuffleArray(T[] arr, Random rnd) {
		int size = arr.length;
		for (int i = size; i > 1; i--)
			swap(arr, i - 1, rnd.nextInt(i));
	}

	private static <T> void swap(T[] arr, int i, int j) {
		T tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	@Override
	public ParameterType getType() {
		return ParameterType.SHUFFLE;
	}

}
