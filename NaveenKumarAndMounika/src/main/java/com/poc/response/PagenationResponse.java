package com.poc.response;

public class PagenationResponse {
private Integer pageNo;
private Integer pageSize;
private Long totalRecords;
private Object empList;
private String message;
private String statusCode;
public String getStatusCode() {
	return statusCode;
}
public void setStatusCode(String statusCode) {
	this.statusCode = statusCode;
}
public Integer getPageNo() {
	return pageNo;
}
public void setPageNo(Integer pageNo) {
	this.pageNo = pageNo;
}
public Integer getPageSize() {
	return pageSize;
}
public void setPageSize(Integer pageSize) {
	this.pageSize = pageSize;
}
public Long getTotalRecords() {
	return totalRecords;
}
public void setTotalRecords(Long totalRecords) {
	this.totalRecords = totalRecords;
}
public Object getEmpList() {
	return empList;
}
public void setEmpList(Object empList) {
	this.empList = empList;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
@Override
public String toString() {
	return "PagenationResponse [pageNo=" + pageNo + ", pageSize=" + pageSize + ", totalRecords=" + totalRecords
			+ ", empList=" + empList + ", message=" + message + ", statusCode=" + statusCode + "]";
}
}
