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

	void setType(EnumTaskType type) {
		this.type = type;
	}

	void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	private String text;

	public TaskDTO() {
	}

	@Override
	public String toString() {
		return "TaskDTO [type=" + type + ", text=" + text + "]";
	}
}
