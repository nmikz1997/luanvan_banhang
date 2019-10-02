package com.luanvan.exception;

public class RollbackException extends RuntimeException{

	private static final long serialVersionUID = 8987377290530217506L;

	public RollbackException() {
		super();
	}
	
	public RollbackException(final String msg, final Throwable cause) {
		super(msg, cause);
	}
	
	public RollbackException(final String msg) {
		super(msg);
	}
	
	public RollbackException(final Throwable cause) {
		super(cause);
	}
}
