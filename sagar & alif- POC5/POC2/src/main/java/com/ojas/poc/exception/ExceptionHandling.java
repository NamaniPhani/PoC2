package com.ojas.poc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ojas.poc.response.Response;

@ControllerAdvice
public class ExceptionHandling {
 
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handlerMethod(MethodArgumentNotValidException e) {
		Response response = new Response();
		response.setMsg(e.getBindingResult().getFieldError().getDefaultMessage());
		response.setStatus(409);
		return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<?> handlerMethod1(RecordNotFoundException e) {
		Response response = new Response();
		response.setMsg(e.getMessage());
		response.setStatus(409);
		return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);

	}

	/*
	 * @ExceptionHandler(MissingServletRequestParameterException.class) public
	 * ResponseEntity<?> handlerMethod2(MissingServletRequestParameterException e) {
	 * Response response = new Response(); response.setMsg(e.getMessage());
	 * response.setStatus(409); return new ResponseEntity<Response>(response,
	 * HttpStatus.BAD_REQUEST); }
	 */
}
