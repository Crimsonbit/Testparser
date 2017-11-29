package at.crimsonbit.testparser.parser.question.tasks;

import at.crimsonbit.testparser.parser.question.EnumTaskType;
import at.crimsonbit.testparser.parser.question.mapping.QMap;

public class StringTask extends Task<String> {
	private static final EnumTaskType type = EnumTaskType.STRING;

	public StringTask(String task) {
		super(task);
	}

	@Override
	public StringTask parse(QMap map) {
		int k = 0, i = 0;
		StringBuilder sb = new StringBuilder();
		while ((i = task.indexOf('$', k)) != -1) {
			String pre = task.substring(k, i);
			sb.append(pre);
			int j = task.indexOf('}', i);
			String num = task.substring(i + 2, j);
			int inum = Integer.parseInt(num);
			sb.append(map.get(inum));
			k = j + 1;
		}
		sb.append(task.substring(k));
		task = sb.toString();
		return this;
	}

	@Override
	public String evaluate() {
		// for String task
		return toString();
	}

	@Override
	public String toString() {
		return task;
	}

	@Override
	public EnumTaskType getType() {
		return type;
	}

}
