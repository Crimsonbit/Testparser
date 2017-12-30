package at.crimsonbit.testparser.parser.dto;

import at.crimsonbit.testparser.api.sheetinterface.ISolutionData;
import at.crimsonbit.testparser.parser.question.EnumTaskType;

/**
 * Data Transfer Object for Solutions. Used in json serialization
 * 
 * @author Alexander Daum
 *
 */
public class SolutionDTO implements ISolutionData {
	private EnumTaskType type;
	private String text;

	void setResult(String result) {
		this.text = result;
	}

	public SolutionDTO() {
	}

	public SolutionDTO(String result) {
		super();
		this.text = result;
	}

	public String getResult() {
		return text;
	}

	@Override
	public EnumTaskType getType() {
		return type;
	}

	@Override
	public String getText() {
		return text;
	}
}
