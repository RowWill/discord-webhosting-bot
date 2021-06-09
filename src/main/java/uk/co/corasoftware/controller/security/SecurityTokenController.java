package uk.co.corasoftware.controller.security;

import uk.co.corasoftware.controller.Controller;
import uk.co.corasoftware.exception.InvalidSecurityTokenException;
import uk.co.corasoftware.exception.MissingSecurityTokenException;
import uk.co.corasoftware.model.security.ApiToken;
import uk.co.corasoftware.util.security.jwt.JwtTokenDecoder;

public class SecurityTokenController extends Controller<ApiToken> {

	public boolean isValidToken(String token) throws MissingSecurityTokenException, InvalidSecurityTokenException {
		if (token == null || token.isEmpty()) {
			throw new MissingSecurityTokenException("Request missing token parameter");
		}

		boolean isValidRequest = JwtTokenDecoder.isValidToken(token);
		if (!isValidRequest) {
			throw new InvalidSecurityTokenException("Invalid token");
		}

		return true;

	}
}
