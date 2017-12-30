package at.crimsonbit.testparser.parser.question.solutions;

import java.util.stream.Stream;

import at.crimsonbit.testparser.api.sheetinterface.ISolutionData;
import at.crimsonbit.testparser.exceptions.RuntimeQuestionException;
import at.crimsonbit.testparser.parser.question.TaskType;
import at.crimsonbit.testparser.parser.question.tasks.Task;

public abstract class Solution<T> extends Task<T> {

	public Solution(String task) {
		super(task);
	}

	public abstract boolean isSolution(Object obj);

	public static Solution<?>[] createSolutions(Stream<? extends ISolutionData> stream) {
		return stream.map(t -> createSolutionFromDTO(t)).toArray(Solution[]::new);
	}

	public static Solution<?> createSolutionFromDTO(ISolutionData data) {
		assert data != null;
		if (data.getType() == null) {
			throw new RuntimeQuestionException("Solution Type was null");
		}
		return TaskType.getTaskType(data.getType()).getSolution(data.getText());
	}
	


}
