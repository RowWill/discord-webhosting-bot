package uk.co.corasoftware.exception;

public class InvalidSecurityTokenException extends Exception {

	private static final long serialVersionUID = -2340183595808201628L;

	public InvalidSecurityTokenException(String message) {
		super(message);
	}
}
