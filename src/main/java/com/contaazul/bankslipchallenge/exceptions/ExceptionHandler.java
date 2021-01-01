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

	@org.springframework.web.bind.annotation.ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentNotValidException.class})
	public ResponseEntity<ResponseDetail> handleNotReadableException(Exception exception,
			HttpServletRequest request, HttpServletResponse response) {

		response.setHeader( CONTENT_TYPE, MediaType.APPLICATION_JSON.toString() );
		ResponseDetail errorDetail = buildErrorDetail( UNPROCESSABLE_ENTITY, "Invalid bankslip provided.The possible reasons are:A field of the provided bankslip was null or with invalid values ");
		return new ResponseEntity<>( errorDetail, null, HttpStatus.UNPROCESSABLE_ENTITY );
	}


	private ResponseDetail buildErrorDetail(String title, String detail) {

		ResponseDetail errorDetail = new ResponseDetail();
		errorDetail.setTitle( title );
		errorDetail.setDetail( detail );

		return errorDetail;
	}

}
