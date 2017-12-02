package at.crimsonbit.testparser.parser.dto;

/**
 * Data Transfer Object for Keys. Used in json serialization
 * 
 * @author Alexander Daum
 *
 */
public class KeyDTO {
	private String type;
	private double minimum;
	private double maximum;
	private String[] values;
	private String expr;
	private int digits;

	public int getDigits() {
		return digits;
	}

	public String getExpr() {
		return expr;
	}

	public KeyDTO() {
		// Parameterless Constructor
	}

	public KeyDTO(String type, int minimum, int maximum, int number, String[] values) {
		super();
		this.type = type;
		this.minimum = minimum;
		this.maximum = maximum;
		this.values = values;
	}

	public String getType() {
		return type;
	}

	public double getMinimum() {
		return minimum;
	}

	public double getMaximum() {
		return maximum;
	}

	public String[] getValues() {
		return values;
	}

}
