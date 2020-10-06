package com.poc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.poc.response.Response;

@ControllerAdvice
public class CustomExceptionController {
	@ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> customException(CustomException cx) {
       Response response = new Response();
        response.setMessage(cx.getLocalizedMessage());
        return new ResponseEntity<Object>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
 

