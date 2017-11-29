package at.crimsonbit.testparser.api;

import at.crimsonbit.testparser.parser.question.Question;

/**
 * API Class for Questions
 * 
 * @author Alexander Daum
 *
 */
public class APIQuestion {
	private final Question q;
	private final UniqueID uid;

	APIQuestion(Question q, long seed, int pre) {
		super();
		this.q = q;
		this.uid = new UniqueID(pre, seed);
	}

	/**
	 * Returns the Question, for getting solutions and Tasks or checking answers
	 * 
	 * @return
	 */
	public Question getQ() {
		return q;
	}

	/**
	 * Returns the seed
	 * 
	 * @return
	 */
	public long getSeed() {
		return uid.getSeed();
	}

	/**
	 * Returns the UID, with the UID the question can be replicated using
	 * {@link TestParser#replicateQuestion(UniqueID)}
	 * 
	 * @return
	 */
	public UniqueID getUID() {
		return uid;
	}

	public String getAnswer() {
		return q.getSolutions();
	}

}
