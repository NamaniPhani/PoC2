package com.ojas.poc.response;

public class Response {
	private Object object;
	private String msg;
	private Integer status;

	public Response() {
		super();
	}

	public Response(Object object, String msg, Integer status) {
		super();
		this.object = object;
		this.msg = msg;
		this.status = status;
	}

	public Response(String msg, Integer status) {
		super();
		this.msg = msg;
		this.status = status;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Response [msg=" + msg + ", status=" + status + "]";
	}

}
