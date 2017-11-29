package at.crimsonbit.testparser.api;

/**
 * A Class to combine an Object that has to be returned with a string message
 * 
 * @author Alexander Daum
 *
 * @param <T>
 */
public class APIResponse<T> {

	private final T response;
	private final String message;

	public static final String SUCCESS_MESSAGE = "success";

	public APIResponse(T response, String message) {
		super();
		this.response = response;
		this.message = message;
	}

	public T getResponse() {
		return response;
	}

	public String getMessage() {
		return message;
	}
}
