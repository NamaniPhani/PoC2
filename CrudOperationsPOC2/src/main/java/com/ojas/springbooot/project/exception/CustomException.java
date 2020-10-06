package com.ojas.springbooot.project.exception;

public class CustomException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private Integer statusCode;
	private String message;

	public CustomException(Integer code, String message) {
		this.statusCode = code;
		this.message = message;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "CustomException [statusCode=" + statusCode + ", message=" + message + "]";
	}

}

