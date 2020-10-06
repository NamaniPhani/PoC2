package com.poc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.poc.response.Response;
public class ExceptionChecker {
@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response> ExceptionChecker(MethodArgumentNotValidException exception){
	Response response = new Response();
	response.setMessage(exception.getBindingResult().getFieldError().getDefaultMessage());
	response.setStatusCode("400");
	return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
}
}