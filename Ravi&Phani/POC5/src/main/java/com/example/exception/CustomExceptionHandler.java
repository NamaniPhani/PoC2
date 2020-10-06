package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice  //New Exception came
public class CustomExceptionHandler {
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<Object> customException(CustomException ex) {
		Response response = new Response();
		response.setMessage(ex.getLocalizedMessage());
		response.setStatusCode("400");
	
		return new ResponseEntity<Object>(response,HttpStatus.UNPROCESSABLE_ENTITY);
		
		
		
	}

}
