package com.compasso.lou.desafio.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 
 * @author klebson.roberto.lou
 *
 */
@ControllerAdvice
public class ResponseException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ResourceBabRequestException.class)
	public ResponseEntity<ErrorDetails> resourceBabRequestException(ResourceBabRequestException exception) {
		ErrorDetails details = new ErrorDetails(HttpStatus.BAD_REQUEST.value(), exception.getMessage());

		return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundtException.class)
	public ResponseEntity<ErrorDetails> resourceNotFoundtException(ResourceNotFoundtException exception) {
		ErrorDetails details = new ErrorDetails(HttpStatus.NOT_FOUND.value(), exception.getMessage());

		return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
	}
}