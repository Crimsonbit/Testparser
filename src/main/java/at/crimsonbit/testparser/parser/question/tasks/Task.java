package at.crimsonbit.testparser.parser.question.tasks;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import at.crimsonbit.testparser.exceptions.IllegalQuestionFormatException;
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
	public static <T> List<Task<?>> createTasks(Stream<String> stream) {
		return stream.map(s -> TaskType.getTaskType(EnumTaskType.STRING).getTask(s)).collect(Collectors.toList());
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

	public abstract EnumTaskType getType();

	/**
	 * Evaluate the Task as T, not as String
	 * 
	 * @return
	 */
	public abstract T evaluate();
}
