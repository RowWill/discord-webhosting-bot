package uk.co.corasoftware.exception;

public class MissingSecurityTokenException extends Exception {

	private static final long serialVersionUID = 2968553205886200708L;

	public MissingSecurityTokenException(String message) {
		super(message);
	}
}
