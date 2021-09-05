package com.compasso.lou.desafio.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author klebson.roberto.lou
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundtException extends RuntimeException {

	private static final long serialVersionUID = -6251471203187753547L;

	public ResourceNotFoundtException(String messagem) {
		super(messagem);
	}
}