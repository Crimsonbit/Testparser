package at.crimsonbit.testparser.parser.question;

import java.util.Arrays;

import at.crimsonbit.testparser.parser.dto.TaskDTO;
import at.crimsonbit.testparser.parser.question.mapping.IKeyAction;
import at.crimsonbit.testparser.parser.question.mapping.QMap;

public class Task {
	private String task;
	private TaskType type;

	public Task(String task, TaskType type) {
		super();
		this.task = task;
		this.type = type;
		if (this.type == null) {
			this.type = TaskType.STRING;
		}
	}

	public static Task[] createTasks(TaskDTO[] data) {
		return Arrays.stream(data).map(t -> new Task(t.getText(), t.getType())).toArray(Task[]::new);
	}

	public static Task[] createTasks(String[] data) {
		return Arrays.stream(data).map(t -> new Task(t, TaskType.STRING)).toArray(Task[]::new);
	}

	public String getText(QMap map) {
		StringBuilder sb = new StringBuilder();
		int i;
		int k = 0;
		while ((i = task.indexOf('$', k)) != -1) {
			String pre = task.substring(k, i);
			sb.append(pre);
			int j = task.indexOf('}', i);
			String num = task.substring(i + 2, j);
			sb.append(map.get(Integer.parseInt(num)));
			k = j + 1;
		}
		sb.append(task.substring(k));
		return sb.toString();
	}

	public Object evaluate(QMap map) {
		switch (type) {
		case STRING:
			return getText(map);
		case NUMBER:
			return evalNumerical(map);
		}
		return null;
	}

	public Number evalNumerical(QMap map) {
		int j = task.indexOf('}');
		String num = task.substring(2, j);
		Object o = map.get(Integer.parseInt(num));
		if (o instanceof Number) {
			return (Number) o;
		} else {
			return null;
		}
	}
}
