package com.contaazul.bankslipchallenge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ExceptionHandler {

	private static final String CONTENT_TYPE = "Content-Type";
	private static final String UNPROCESSABLE_ENTITY = "Unprocessable Entity";
	private static final String NOT_FOUND = "Not Found";
	private static final String BAD_REQUEST = "Bad Request";

	@org.springframework.web.bind.annotation.ExceptionHandler({ HttpMessageNotReadableException.class,
			MethodArgumentNotValidException.class })
	public ResponseEntity<ResponseDetail> handleNotReadableException(Exception exception,
			HttpServletRequest request, HttpServletResponse response) {

		response.setHeader( CONTENT_TYPE, MediaType.APPLICATION_JSON.toString() );
		ResponseDetail errorDetail = buildErrorDetail( UNPROCESSABLE_ENTITY,
				"Invalid bankslip provided.The possible reasons are:A field of the provided bankslip was null or with invalid values " );
		return new ResponseEntity<>( errorDetail, null, HttpStatus.UNPROCESSABLE_ENTITY );
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(BankslipNotFoundException.class)
	public ResponseEntity<ResponseDetail> handleBankslipNotFoundException(Exception exception,
			HttpServletRequest request, HttpServletResponse response) {

		response.setHeader( CONTENT_TYPE, MediaType.APPLICATION_JSON.toString() );
		ResponseDetail errorDetail = buildErrorDetail( NOT_FOUND, "Bankslip not found with the specified id" );
		return new ResponseEntity<>( errorDetail, null, HttpStatus.NOT_FOUND );
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(BusinessException.class)
	public ResponseEntity<ResponseDetail> handleBusinessException(Exception exception,
			HttpServletRequest request, HttpServletResponse response) {

		response.setHeader( CONTENT_TYPE, MediaType.APPLICATION_JSON.toString() );
		ResponseDetail errorDetail = buildErrorDetail( BAD_REQUEST, exception.getMessage() );
		return new ResponseEntity<>( errorDetail, null, HttpStatus.BAD_REQUEST );
	}

	private ResponseDetail buildErrorDetail(String title, String detail) {

		ResponseDetail errorDetail = new ResponseDetail();
		errorDetail.setTitle( title );
		errorDetail.setDetail( detail );

		return errorDetail;
	}

}
