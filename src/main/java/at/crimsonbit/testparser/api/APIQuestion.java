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
	 * {@link JsonTestParser#replicateQuestion(UniqueID)}
	 * 
	 * @return
	 */
	public UniqueID getUID() {
		return uid;
	}

	public String getAnswer() {
		return q.getSolutionsAsString();
	}

	/**
	 * Checks if the Object is a Solution of the task
	 * 
	 * @param obj
	 * @param task
	 * @return
	 */
	public boolean isSolution(Object obj, int task) {
		return q.isSolution(obj, task);
	}

	public int numTasks() {
		return q.numTasks();
	}
	
	public int numSolution() {
		return q.numSolutions();
	}

	/**
	 * Checks for each Object in solutions if it solves the corresponding task, the
	 * length of the array has to be {@link APIQuestion#numTasks()}
	 * 
	 * @param obj
	 * @param task
	 * @return
	 */
	public boolean[] areSolutions(Object[] solutions) {
		boolean[] ret = new boolean[solutions.length];
		for (int i = 0; i < solutions.length; i++) {
			ret[i] = q.isSolution(solutions[i], i);
		}
		return ret;
	}

	public String[] getHints() {
		return q.getHints();
	}
	
	@Override
	public String toString() {
		return q.getTasksAsString();
	}

}
