package com.ojas.module.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	 public ResponseEntity<?>exceptionHandle(MethodArgumentNotValidException methodArg){
		 Response response = new Response();
		 response.setMessage(methodArg.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<Response>(response,HttpStatus.BAD_REQUEST);
		 
	 }

}
