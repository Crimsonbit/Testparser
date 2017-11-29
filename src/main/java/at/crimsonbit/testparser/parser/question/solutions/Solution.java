package at.crimsonbit.testparser.parser.question.solutions;

import java.util.Arrays;

import at.crimsonbit.testparser.exceptions.RuntimeQuestionException;
import at.crimsonbit.testparser.parser.dto.TaskDTO;
import at.crimsonbit.testparser.parser.question.TaskType;
import at.crimsonbit.testparser.parser.question.tasks.Task;

public abstract class Solution<T> extends Task<T> {

	public Solution(String task) {
		super(task);
	}

	public abstract boolean isSolution(Object obj);

	public static Solution<?>[] createSolutions(TaskDTO[] data) {
		return Arrays.stream(data).map(t -> createSolutionFromDTO(t)).toArray(Solution[]::new);
	}

	public static Solution<?> createSolutionFromDTO(TaskDTO data) {
		if (data.getType() == null) {
			throw new RuntimeQuestionException("Solution Type was null");
		}
		return TaskType.getTaskType(data.getType()).getSolution(data.getText());
	}

}
