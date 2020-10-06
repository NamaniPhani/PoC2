package com.ojas.poc.exception;

public class RecordNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

	public RecordNotFoundException() {
		super();
	}

	public RecordNotFoundException(String message) {
		super();
		this.message = message; 
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "RecordNotFoundException [message=" + message + "]";
	}

}
