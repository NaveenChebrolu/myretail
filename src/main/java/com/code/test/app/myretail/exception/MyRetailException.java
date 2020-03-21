package com.code.test.app.myretail.exception;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author naveen
 *
 */

public class MyRetailException extends Exception {

	private static final long serialVersionUID = 1146643224500226255L;
	private int errorCode;
	private String errorMessage;

	public MyRetailException() {
		super();
	}

	public MyRetailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		errorCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
	}

	public MyRetailException(String message, Throwable cause) {
		super(message, cause);
		errorCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
	}

	public MyRetailException(String message) {
		super(message);
		errorCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
	}

	public MyRetailException(Throwable cause) {
		super(cause);
		errorCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
	}

	public MyRetailException(final int errorCode, final String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
