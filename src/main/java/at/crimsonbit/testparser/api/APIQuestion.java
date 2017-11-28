package at.crimsonbit.testparser.api;

import at.crimsonbit.testparser.parser.question.Question;

public class APIQuestion {
	private final Question q;
	private final UniqueID uid;

	public APIQuestion(Question q, long seed, int pre) {
		super();
		this.q = q;
		this.uid = new UniqueID(pre, seed);
	}

	public Question getQ() {
		return q;
	}

	public long getSeed() {
		return uid.getSeed();
	}

	public UniqueID getUID() {
		return uid;
	}

	public String getAnswer() {
		return q.getSolutions();
	}

}
