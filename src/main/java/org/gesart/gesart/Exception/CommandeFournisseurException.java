package org.gesart.gesart.Exception;

public class CommandeFournisseurException extends RuntimeException {
	public CommandeFournisseurException(String message) {
		super(message);
	}

	public CommandeFournisseurException(String message, Throwable cause) {
		super(message, cause);
	}

}
