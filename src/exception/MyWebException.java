package exception;

public class MyWebException extends Exception {
	private String errorCode;

	public String getErrorCode() {
		return errorCode;
	}

	public MyWebException(String msg, String errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}
}
