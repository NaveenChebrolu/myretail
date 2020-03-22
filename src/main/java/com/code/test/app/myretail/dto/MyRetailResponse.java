package com.code.test.app.myretail.dto;

public class MyRetailResponse {

	private int statusCode;

	private String errorMessage;

	public MyRetailResponse(int statusCode, String errorMessage) {
		this.errorMessage = errorMessage;
		this.statusCode = statusCode;
	}

	/**
	 * @return the statusCode
	 */
	public final int getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public final void setStatusCode(final int statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the errorMessage
	 */
	public final String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public final void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
