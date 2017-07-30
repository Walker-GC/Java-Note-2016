package cn.tedu.note.service;

public class NameOrPasswordException extends RuntimeException {

	public NameOrPasswordException() {
	}
	public NameOrPasswordException(String message) {
		super(message);
	}
	public NameOrPasswordException(Throwable cause) {
		super(cause);
	}
	public NameOrPasswordException(String message, Throwable cause) {
		super(message, cause);
	}
	public NameOrPasswordException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
