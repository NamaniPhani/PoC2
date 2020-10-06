
package com.ojas.task.ExceptionHandler;

import javax.validation.UnexpectedTypeException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ojas.task.model.Response;

@ControllerAdvice
public class CustomHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handlerMethod(MethodArgumentNotValidException e) {
		Response response = new Response();
		response.setMsg(e.getBindingResult().getFieldError().getDefaultMessage());
		response.setStatus("409");
		return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
	}  

	@ExceptionHandler(UnexpectedTypeException.class)
	public ResponseEntity<?> unExpectedHandlerMethod(UnexpectedTypeException e) {
		Response response = new Response();

		response.setMsg("Phone Number Should Contain 10 Digits");
		response.setStatus("400");
		return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);

	}

}
