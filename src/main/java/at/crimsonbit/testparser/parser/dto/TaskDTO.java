package at.crimsonbit.testparser.parser.dto;

import at.crimsonbit.testparser.parser.question.EnumTaskType;

/**
 * Data Transfer Object for Tasks. Used in json serialization
 * 
 * @author Alexander Daum
 *
 */
public class TaskDTO {

	private EnumTaskType type;

	public EnumTaskType getType() {
		return type == null ? EnumTaskType.STRING : type;
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
