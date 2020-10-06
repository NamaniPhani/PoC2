package com.example.exception;

public class Response {
	private String message;
	private String statusCode;
	private Object object;

	public Response() {
		super();
	}

	public Response(String message, String statusCode, Object object) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.object = object;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String i) {
		this.statusCode = i;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	@Override
	public String toString() {
		return "Response [message=" + message + ", statusCode=" + statusCode + ", object=" + object + "]";
	}

}
