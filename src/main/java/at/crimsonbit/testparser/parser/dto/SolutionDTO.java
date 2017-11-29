package at.crimsonbit.testparser.parser.dto;
/**
 * Data Transfer Object for Solutions. Used in json serialization
 * 
 * @author Alexander Daum
 *
 */
public class SolutionDTO {
	private String result;

	public SolutionDTO() {
	}

	public SolutionDTO(String result) {
		super();
		this.result = result;
	}

	public String getResult() {
		return result;
	}
}
