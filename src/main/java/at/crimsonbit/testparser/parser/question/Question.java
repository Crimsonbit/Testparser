package at.crimsonbit.testparser.parser.question;

import java.util.Random;

import at.crimsonbit.testparser.exceptions.IllegalQuestionFormatException;
import at.crimsonbit.testparser.parser.question.mapping.QMap;
import at.crimsonbit.testparser.parser.question.solutions.Solution;
import at.crimsonbit.testparser.parser.question.tasks.Task;

public class Question {
	private QMap map;
	private AbstractQuestion question;
	private Task<?>[] tasks;
	private Solution<?>[] solutions;

	Question(AbstractQuestion q, Random r) throws IllegalQuestionFormatException {
		this.question = q;
		map = new QMap(question.keys);
		map.calculate(r);
		this.tasks = q.tasks.clone();
		this.solutions = q.solve.clone();
		for (Task<?> t : tasks) {
			t.parse(map);
		}
		for (Solution<?> s : solutions) {
			s.parse(map);
		}
	}

	@Override
	public String toString() {
		return getTasks();
	}

	/**
	 * Returns all Tasks as one string with newlines in between
	 * 
	 * @return
	 */
	public String getTasks() {
		StringBuilder sb = new StringBuilder();
		for (Task<?> t : tasks) {
			sb.append(t.toString());
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

	/**
	 * Returns the Task number n
	 * 
	 * @param n
	 *            The Number of the Task
	 * @return
	 */
	public String getTask(int n) {
		return tasks[n].toString();
	}

	/**
	 * Returns all Solutions as one string with newlines in between
	 * 
	 * @return
	 */
	public String getSolutions() {
		StringBuilder sb = new StringBuilder();
		for (Task<?> t : question.solve) {
			sb.append(t.toString());
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

	/**
	 * Checks if the Object solves the Task n, the Object can be anything. If the
	 * solution expects a String, the {@link Object#toString()} method is called, if
	 * the Solution expects a number, it checks, if the object is a number, if not,
	 * then it tries to parse the toString String as Double with
	 * {@link Double#parseDouble(String)} and then checks the solution
	 * 
	 * @param obj
	 *            The attempt at solving
	 * @param task
	 *            The Task number
	 * @return true if and only if obj solves the task n
	 */
	public boolean isSolution(Object obj, int task) {
		return solutions[task].isSolution(obj);
	}

	/**
	 * Return the Solution of the Task n as String
	 * 
	 * @param n
	 *            the Task
	 * @return
	 */
	public String getSolution(int n) {
		return question.solve[n].toString();
	}

	/**
	 * 
	 * @return The amount of tasks
	 */
	public int getTaskSize() {
		return tasks.length;
	}

}
