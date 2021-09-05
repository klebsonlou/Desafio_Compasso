package com.compasso.lou.desafio.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author klebson.roberto.lou
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceBabRequestException extends RuntimeException {

	private static final long serialVersionUID = -7885662184140445383L;

	public ResourceBabRequestException(String messagem) {
		super(messagem);
	}
}