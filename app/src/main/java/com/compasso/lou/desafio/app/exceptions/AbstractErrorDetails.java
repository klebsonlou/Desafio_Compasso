package com.compasso.lou.desafio.app.exceptions;

/**
 * 
 * @author klebson.roberto.lou
 *
 */
abstract class AbstractErrorDetails {
	private Integer status_code;
	private String message;
	
	protected AbstractErrorDetails(Integer statusCode, String message) {
		super();
		this.status_code = statusCode;
		this.message = message;
	}
	
	public Integer getStatus_code() {
		return status_code;
	}
	public void setStatus_Code(Integer statusCode) {
		this.status_code = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}