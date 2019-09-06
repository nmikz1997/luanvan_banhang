package com.luanvan.exception;

public class NotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public NotFoundException() {
		super();
	}
	
	public NotFoundException(final String msg, final Throwable cause) {
		super(msg, cause);
	}
	
	public NotFoundException(final String msg) {
		super(msg);
	}
	
	public NotFoundException(final Throwable cause) {
		super(cause);
	}
}
