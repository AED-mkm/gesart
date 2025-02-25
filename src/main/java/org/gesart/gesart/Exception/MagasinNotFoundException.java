package org.gesart.gesart.Exception;

public class MagasinNotFoundException extends RuntimeException{
	public MagasinNotFoundException(String message) {
		super(message);
	}

	public MagasinNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
