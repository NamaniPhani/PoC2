package com.ojas.obs.employmentdetails.exception;

import javax.validation.UnexpectedTypeException;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ojas.obs.employmentdetails.model.Response;

@ControllerAdvice
public class CustomHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handlerMethod(MethodArgumentNotValidException e) {
		Response response = new Response();
		response.setMsg(e.getBindingResult().getFieldError().getDefaultMessage());
		response.setStatuscode("409");
		return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UnexpectedTypeException.class)
	public ResponseEntity<?> unExpectedHandlerMethod(UnexpectedTypeException e) {
		Response response = new Response();
		response.setMsg("Phone Number Should Contain 10 Digits");
		response.setStatuscode("400");
		return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);

	}
}
