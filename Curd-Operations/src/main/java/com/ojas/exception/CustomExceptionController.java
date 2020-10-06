package com.ojas.exception;

import static com.ojas.util.Constants.FAIL;
import static com.ojas.util.Constants.FAIL_STATUS;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ojas.response.Response;

@ControllerAdvice
public class CustomExceptionController   {
	@ExceptionHandler(value = CustomException.class)
	public ResponseEntity<Object> customException(CustomException cex) {
		String localizedMessage = cex.getLocalizedMessage();
		Response response = new Response();
		response.setStatusCode(FAIL_STATUS);
		response.setStatus(FAIL);
		response.setMsg(localizedMessage);
		return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);

	} 
 
}
 