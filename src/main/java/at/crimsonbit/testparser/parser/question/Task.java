package at.crimsonbit.testparser.parser.question;

import java.util.Arrays;

import at.crimsonbit.testparser.parser.dto.TaskDTO;
import at.crimsonbit.testparser.parser.question.mapping.QMap;

public class Task {
	private String task;

	public Task(String task) {
		super();
		this.task = task;
	}

	public static Task[] createTasks(TaskDTO[] data) {
		return Arrays.stream(data).map(t -> new Task(t.getText())).toArray(Task[]::new);
	}
	public static Task[] createTasks(String[] data) {
		return Arrays.stream(data).map(t -> new Task(t)).toArray(Task[]::new);
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
		return sb.toString();
	}
}
