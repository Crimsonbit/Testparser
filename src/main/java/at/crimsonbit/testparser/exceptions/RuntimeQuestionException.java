package at.crimsonbit.testparser.exceptions;

public class RuntimeQuestionException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8713114113496621919L;

	public RuntimeQuestionException() {
		super();
	}

	public RuntimeQuestionException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public RuntimeQuestionException(String message, Throwable cause) {
		super(message, cause);
	}

	public RuntimeQuestionException(String message) {
		super(message);
	}

	public RuntimeQuestionException(Throwable cause) {
		super(cause);
	}

}
