package exception;

public class MyWebException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;

	public String getErrorCode() {
		return errorCode;
	}

	public MyWebException(String msg, String errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}
}
