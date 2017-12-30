package at.crimsonbit.testparser.parser.dto;

import at.crimsonbit.testparser.api.sheetinterface.IKeyData;

/**
 * Data Transfer Object for Keys. Used in json serialization
 * 
 * @author Alexander Daum
 *
 */
public class KeyDTO implements IKeyData{
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

	void setType(String type) {
		this.type = type;
	}

	void setMinimum(double minimum) {
		this.minimum = minimum;
	}

	void setMaximum(double maximum) {
		this.maximum = maximum;
	}

	void setValues(String[] values) {
		this.values = values;
	}

	void setExpr(String expr) {
		this.expr = expr;
	}

	void setDigits(int digits) {
		this.digits = digits;
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

	public double getMin() {
		return minimum;
	}

	public double getMax() {
		return maximum;
	}

	public String[] getValues() {
		return values;
	}



}
