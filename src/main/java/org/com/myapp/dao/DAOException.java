package org.com.myapp.dao;

@SuppressWarnings("serial")
public class DAOException extends Exception {

	private String message;

	public DAOException() {

	}

	public DAOException(String message) {
		super(message);
		this.message = message;
	}

	public DAOException(Throwable cause) {
		super(cause);
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return message;
	}

}
