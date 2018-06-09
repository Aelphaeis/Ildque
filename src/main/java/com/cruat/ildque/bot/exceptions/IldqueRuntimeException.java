package com.cruat.ildque.bot.exceptions;

public class IldqueRuntimeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public IldqueRuntimeException(String msg) {
		super(msg);
	}

	public IldqueRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}
}
