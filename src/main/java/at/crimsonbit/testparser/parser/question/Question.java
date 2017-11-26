package at.crimsonbit.testparser.parser.question;

import java.util.Random;

import at.crimsonbit.testparser.parser.question.mapping.QMap;

public class Question {
	private QMap map;
	private AbstractQuestion question;

	Question(AbstractQuestion q, Random r) {
		this.question = q;
		map = new QMap(question.keys);
		map.calculate(r);
	}

	@Override
	public String toString() {
		return getTasks();
	}

	public String getTasks() {
		StringBuilder sb = new StringBuilder();
		for (Task t : question.tasks) {
			sb.append(t.getText(map));
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

	public String getTask(int n) {
		return question.tasks[n].getText(map);
	}

	public String getSolutions() {
		StringBuilder sb = new StringBuilder();
		for (Task t : question.solve) {
			sb.append(t.getText(map));
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

	public String getSolution(int n) {
		return question.solve[n].getText(map);
	}

}
