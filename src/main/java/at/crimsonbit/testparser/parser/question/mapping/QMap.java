package at.crimsonbit.testparser.parser.question.mapping;

import java.util.Random;

public class QMap {
	private IKey<?>[] keys;
	private Object[] vals;
	private Object[] valsForSol;

	public QMap(IKey<?>[] keys) {
		this.keys = keys;
	}

	public void calculate(Random random) {
		vals = new Object[keys.length];
		valsForSol = new Object[keys.length];
		for (int i = 0; i < keys.length; i++) {
			keys[i] = keys[i].parse(random);
		}
		for (int i = 0; i < keys.length; i++) {
			vals[i] = keys[i].get();
		}
		for (int i = 0; i < keys.length; i++) {
			valsForSol[i] = keys[i].getForSol();
		}

	}

	public Object get(int i) {
		Object r = vals[i];
		if (r instanceof IKeyAction) {
			r = ((IKeyAction) r).compute(vals);
			vals[i] = r;
		}
		return r;
	}
	
	public Object getForSol(int i) {
		Object r = valsForSol[i];
		if (r instanceof IKeyAction) {
			r = ((IKeyAction) r).compute(valsForSol);
			valsForSol[i] = r;
		}
		return r;
	}
}
