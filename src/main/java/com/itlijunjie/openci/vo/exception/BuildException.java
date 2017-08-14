package com.itlijunjie.openci.vo.exception;

public class BuildException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BuildException() {
		super();
	}

	public BuildException(String message, Throwable cause) {
		super(message, cause);
	}

	public BuildException(String message) {
		super(message);
	}
	public BuildException(Throwable cause) {
		super(cause);
	}
	
}
