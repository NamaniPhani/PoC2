package com.ojas.obs.employmentdetails.model;

public class Response {

	private Object object;

	private String msg;

	private String statuscode;

	public Response(Object object, String msg, String statuscode) {
		super();
		this.object = object;
		this.msg = msg;
		this.statuscode = statuscode;
	}

	public Response() {
		super();
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(String statuscode) {
		this.statuscode = statuscode;
	}

	@Override
	public String toString() {
		return "Response [object=" + object + ", msg=" + msg + ", statuscode=" + statuscode + "]";
	}

}
