package com.itlijunjie.openci.vo.exception;

public class JenkinsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public JenkinsException() {
		super();
	}

	public JenkinsException(String message, Throwable cause) {
		super(message, cause);
	}

	public JenkinsException(String message) {
		super(message);
	}
	public JenkinsException(Throwable cause) {
		super(cause);
	}
	
}
