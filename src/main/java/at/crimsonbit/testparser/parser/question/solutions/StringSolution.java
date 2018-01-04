package at.crimsonbit.testparser.parser.question.solutions;

import at.crimsonbit.testparser.exceptions.IllegalQuestionFormatException;
import at.crimsonbit.testparser.parser.question.EnumTaskType;
import at.crimsonbit.testparser.parser.question.mapping.QMap;

public class StringSolution extends Solution<String> {

	private static final EnumTaskType type = EnumTaskType.STRING;

	public StringSolution(String task) {
		super(task);
	}

	@Override
	public StringSolution parse(QMap map) throws IllegalQuestionFormatException {
		int k = 0, i = 0;
		StringBuilder sb = new StringBuilder();
		while ((i = task.indexOf('$', k)) != -1) {
			int j = task.indexOf('}', i);
			String num = task.substring(i + 2, j);
			int inum = Integer.parseInt(num);
			try {
				sb.append(map.get(inum));
			} catch (ArrayIndexOutOfBoundsException e) {
				throw new IllegalQuestionFormatException(
						String.format("Cannot access index %d in map, solution is %s", inum, task), e);
			}
			k = j + 1;
		}
		sb.append(task.substring(k));
		task = sb.toString();
		return this;
	}

	@Override
	public String evaluate() {
		return toString();
	}

	@Override
	public String toString() {
		return task;
	}

	@Override
	public boolean isSolution(Object obj) {
		return task.equalsIgnoreCase(obj.toString());
	}

	@Override
	public EnumTaskType getType() {
		return type;
	}

}
