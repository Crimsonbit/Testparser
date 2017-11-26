package at.crimsonbit.testparser.parser.question.mapping;

import java.util.Random;

public class QMap {
	private IKey<?>[] keys;
	private Object[] vals;

	public QMap(IKey<?>[] keys) {
		this.keys = keys;
	}

	public void calculate(Random random) {
		vals = new Object[keys.length];
		for (int i = 0; i < keys.length; i++) {
			vals[i] = keys[i].get(random);
		}
	}

	public Object get(int i) {
		Object r = vals[i];
		if (r instanceof IKeyAction) {
			r = ((IKeyAction) r).compute(vals);
		}
		return r;
	}
}
