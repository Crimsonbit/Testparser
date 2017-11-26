package at.crimsonbit.testparser.parser.dto;

public class KeyDTO {
	private String type;
	private double minimum;
	private double maximum;
	private String[] values;
	private String value;

	public String getValue() {
		return value;
	}

	public KeyDTO() {
		// TODO Auto-generated constructor stub
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
