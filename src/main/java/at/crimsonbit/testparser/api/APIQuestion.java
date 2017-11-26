package at.crimsonbit.testparser.api;

import at.crimsonbit.testparser.parser.question.Question;

public class APIQuestion {
	private final Question q;
	private final long seed;
	private final String prefix;

	public APIQuestion(Question q, long seed, String prefix) {
		super();
		this.q = q;
		this.seed = seed;
		this.prefix = prefix;
	}

	public Question getQ() {
		return q;
	}

	public long getSeed() {
		return seed;
	}

	public String getPrefix() {
		return prefix;
	}

}
