package com.amine.tollparking.errorhandler;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

	private static final long serialVersionUID = 1016368150710232479L;
	
	private HttpStatus httpStatus;

	public ApiException(String exception) {
        super(exception);
    }
	
	public ApiException(String message, HttpStatus status) {
		super(message);
		this.httpStatus = status;
	}

	public ApiException(String message, HttpStatus status, Exception rootCause) {
		super(message, rootCause);
		this.httpStatus = status;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
}