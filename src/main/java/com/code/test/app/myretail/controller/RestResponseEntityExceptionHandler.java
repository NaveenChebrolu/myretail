package com.code.test.app.myretail.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.code.test.app.myretail.dto.MyRetailResponse;
import com.code.test.app.myretail.exception.MyRetailException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<MyRetailResponse> exceptionHandler(MyRetailException ex) {
		logger.error("Exception Occured: {}", ex);
		MyRetailResponse error = new MyRetailResponse(ex.getErrorCode(), ex.getErrorMessage());
		return new ResponseEntity<MyRetailResponse>(error, HttpStatus.valueOf(ex.getErrorCode()));
	}
}
