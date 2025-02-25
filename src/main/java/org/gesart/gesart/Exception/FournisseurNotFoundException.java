package org.gesart.gesart.Exception;

public class FournisseurNotFoundException extends RuntimeException {

	public FournisseurNotFoundException(String message) {
		super(message);
	}
	public FournisseurNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
