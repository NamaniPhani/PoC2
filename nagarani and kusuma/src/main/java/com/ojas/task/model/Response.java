package com.ojas.task.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Response {

	private Object object;
	private String msg;
	private String status;

	public Response(Object object, String msg, String status) {
		super();
		this.object = object;
		this.msg = msg;
		this.status = status;
	}

	public Response() {
		super();
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	@Override
	public String toString() {
		return "Response [msg=" + msg + ", status=" + status + ", object=" + object + "]";
	}

}
