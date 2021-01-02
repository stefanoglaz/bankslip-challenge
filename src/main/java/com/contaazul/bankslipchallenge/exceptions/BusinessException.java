package com.contaazul.bankslipchallenge.exceptions;

public class BusinessException extends RuntimeException {

	public BusinessException() {
		super();
	}

	public BusinessException(String message) {
		super( message );
	}
}
