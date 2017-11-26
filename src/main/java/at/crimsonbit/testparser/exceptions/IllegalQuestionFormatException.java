package at.crimsonbit.testparser.exceptions;

public class IllegalQuestionFormatException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 212394679288514187L;

	public IllegalQuestionFormatException() {
		super();
	}

	public IllegalQuestionFormatException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IllegalQuestionFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalQuestionFormatException(String message) {
		super(message);
	}

	public IllegalQuestionFormatException(Throwable cause) {
		super(cause);
	}

}
