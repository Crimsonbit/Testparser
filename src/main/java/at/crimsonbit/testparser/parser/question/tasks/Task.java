package at.crimsonbit.testparser.parser.question.tasks;

import java.util.Arrays;

import at.crimsonbit.testparser.exceptions.IllegalQuestionFormatException;
import at.crimsonbit.testparser.parser.dto.TaskDTO;
import at.crimsonbit.testparser.parser.question.EnumTaskType;
import at.crimsonbit.testparser.parser.question.TaskType;
import at.crimsonbit.testparser.parser.question.mapping.QMap;

/**
 * Superclass for all Tasks and Solutions
 * 
 * @author Alexander Daum
 *
 * @param <T>
 *            the type of the return value of the Tasks evaluation
 */
public abstract class Task<T> {
	protected String task;

	/**
	 * Create a new Task with specific name
	 * 
	 * @param task
	 */
	protected Task(String task) {
		super();
		this.task = task;
	}

	/**
	 * Creates a new Task array from an Array of TaskDTO
	 * 
	 * @param data
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> Task<T>[] createTasks(TaskDTO[] data) {
		return Arrays.stream(data).map(t -> TaskType.getTaskType(t.getType()).getTask(t.getText()))
				.toArray(Task[]::new);
	}

	/**
	 * Creates a new Task array from an Array of Strings
	 * 
	 * @param data
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> Task<T>[] createTasks(String[] data) {
		return Arrays.stream(data).map(t -> TaskType.getTaskType(EnumTaskType.STRING).getTask(t)).toArray(Task[]::new);
	}

	public abstract String toString();

	/**
	 * Evaluates this task and returns a task, that can deliver solutions, or values
	 * 
	 * @throws IllegalQuestionFormatException
	 */
	public abstract Task<T> parse(QMap map) throws IllegalQuestionFormatException;

	/**
	 * Returns the Type of the Task as an {@link EnumTaskType}
	 * 
	 * @return
	 */
	public abstract EnumTaskType getType();

	/**
	 * Evaluate the Task as T, not as String
	 * 
	 * @return
	 */
	public abstract T evaluate();
}
