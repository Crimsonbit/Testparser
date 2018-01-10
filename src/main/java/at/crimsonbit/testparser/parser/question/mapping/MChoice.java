package at.crimsonbit.testparser.parser.question.mapping;

import java.util.Random;

import at.crimsonbit.testparser.api.sheetinterface.IKeyData;
import at.crimsonbit.testparser.parser.question.ParameterType;

public class MChoice implements IKey<String> {

	private String[] values;
	private int gpnum;
	private String[] shuffled;

	public static MChoice create(IKeyData data) {
		return new MChoice(data.getValues(), data.getGPNum());
	}

	public MChoice(String[] values, int gpnum) {
		this(values, gpnum, null);
	}
	
	public MChoice(String[] values, int gpnum, String[] shuffled) {
		this.values = values;
		this.gpnum = gpnum;
		this.shuffled = shuffled;
	}

	@Override
	public IKey<String> parse(Random random) {
		String[] s = values.clone();
		shuffleArray(s, random);
		return new MChoice(values, gpnum, s);
	}

	@Override
	public String get() {
		StringBuilder sb = new StringBuilder();
		char letter = 'a';
		for (String s : shuffled) {
			sb.append(letter++);
			sb.append(')');
			sb.append(s);
			sb.append(' ');
		}
		return sb.toString();
	}

	@Override
	public String getForSol() {
		char letter = 'a';
		StringBuilder sb = new StringBuilder();
		for (String s : shuffled) {
			for (int i = 0; i < values.length; i++) {
				int isSol = (gpnum & (1 << i));
				if (s.equals(values[i]) && (isSol != 0)) {
					sb.append(letter);
					sb.append(',');
				}
			}
			letter++;
		}
		sb.deleteCharAt(sb.length() - 1);
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
