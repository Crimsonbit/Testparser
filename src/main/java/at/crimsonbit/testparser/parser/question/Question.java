package at.crimsonbit.testparser.parser.question;

import java.util.List;
import java.util.Random;

import at.crimsonbit.testparser.exceptions.IllegalQuestionFormatException;
import at.crimsonbit.testparser.parser.question.mapping.QMap;
import at.crimsonbit.testparser.parser.question.solutions.Solution;
import at.crimsonbit.testparser.parser.question.tasks.StringTask;
import at.crimsonbit.testparser.parser.question.tasks.Task;

public class Question {
	private QMap map;
	private AbstractQuestion question;
	private Solution<?>[] solutions;

	Question(AbstractQuestion q, Random r) throws IllegalQuestionFormatException {
		this.question = q;
		map = new QMap(question.keys);
		map.calculate(r);
		this.solutions = q.solve.clone();
		for (Task<?> t : q.tasks) {
			t.parse(map);
		}
		for (Solution<?> s : solutions) {
			s.parse(map);
		}
	}

	@Override
	public String toString() {
		return getTasksAsString();
	}

	/**
	 * Returns all Tasks as one string with newlines in between
	 * 
	 * @return
	 */
	public String getTasksAsString() {
		StringBuilder sb = new StringBuilder();
		for (Task<?> t : question.tasks) {
			sb.append(t.toString());
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

	public List<Task<?>> getTasks() {
		return question.tasks;
	}

	/**
	 * Returns the Task number n
	 * 
	 * @param n
	 *            The Number of the Task
	 * @return
	 */
	public String getTask(int n) {
		return question.tasks.get(n).toString();
	}

	/**
	 * Returns all Solutions as one string with newlines in between
	 * 
	 * @return
	 */
	public String getSolutionsAsString() {
		StringBuilder sb = new StringBuilder();
		for (Task<?> t : question.solve) {
			sb.append(t.toString());
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

	public Solution<?>[] getSolutions() {
		return solutions;
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
	public int numTasks() {
		return question.tasks.size();
	}

	public int numSolutions() {
		return solutions.length;
	}

	public String[] getHints() {
		return question.help.stream().map(this::evalHint).toArray(String[]::new);
	}

	public String getHint(int n) {
		String helpn = question.help.get(n);
		return evalHint(helpn);
	}

	private String evalHint(String s) {
		StringTask t = new StringTask(s);
		return t.parse(map).evaluate();
	}

	public String getName() {
		return question.name;
	}
}
