package at.crimsonbit.testparser.parser.dto;

import at.crimsonbit.testparser.parser.question.TaskType;
/**
 * Data Transfer Object for Tasks. Used in json serialization
 * 
 * @author Alexander Daum
 *
 */
public class TaskDTO {

	private TaskType type;

	public TaskType getType() {
		return type;
	}

	public TaskDTO(String text) {
		super();
		this.text = text;
	}

	public String getText() {
		return text;
	}

	private String text;

	public TaskDTO() {
		// TODO Auto-generated constructor stub
	}
}
