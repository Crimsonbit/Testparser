package at.crimsonbit.testparser.parser.dto;

public class QuestionDTO {
	private String name;
	private String subject;
	private TaskDTO[] task;
	private String[] hints;
	private String[] solution;
	private KeyDTO[] keys;

	public QuestionDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public String getSubject() {
		return subject;
	}

	public String[] getHints() {
		return hints;
	}

	public String[] getSolution() {
		return solution;
	}

	public KeyDTO[] getKeys() {
		return keys;
	}

	public TaskDTO[] getTask() {
		return task;
	}

	public QuestionDTO(String name, String subject, TaskDTO[] task, String[] hints, String[] solution, KeyDTO[] keys) {
		super();
		this.name = name;
		this.subject = subject;
		this.hints = hints;
		this.solution = solution;
		this.keys = keys;
		this.task = task;
	}

}
