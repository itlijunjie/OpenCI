package com.itlijunjie.openci.vo.exception;

public class AppException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AppException() {
		super();
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppException(String message) {
		super(message);
	}
	public AppException(Throwable cause) {
		super(cause);
	}
	
}
