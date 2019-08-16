package com.luanvan.exception;

public class NotFoundException extends RuntimeException{
	
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
