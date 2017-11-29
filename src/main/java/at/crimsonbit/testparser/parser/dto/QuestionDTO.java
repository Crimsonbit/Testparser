package at.crimsonbit.testparser.parser.dto;
/**
 * Data Transfer Object for Questions. Used in json serialization
 * 
 * @author Alexander Daum
 *
 */
public class QuestionDTO {
	private String name;
	private String subject;
	private TaskDTO[] task;
	private String[] hints;
	private TaskDTO[] solution;
	private KeyDTO[] keys;
	private int difficulty;

	public QuestionDTO() {
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

	public int getDifficulty() {
		return difficulty;
	}

	public TaskDTO[] getSolution() {
		return solution;
	}

	public KeyDTO[] getKeys() {
		return keys;
	}

	public TaskDTO[] getTask() {
		return task;
	}

	public QuestionDTO(String name, String subject, TaskDTO[] task, String[] hints, TaskDTO[] solution, KeyDTO[] keys) {
		super();
		this.name = name;
		this.subject = subject;
		this.hints = hints;
		this.solution = solution;
		this.keys = keys;
		this.task = task;
	}

}
