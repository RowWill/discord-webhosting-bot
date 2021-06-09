package uk.co.corasoftware.controller.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.corasoftware.controller.Controller;
import uk.co.corasoftware.exception.InvalidSecurityTokenException;
import uk.co.corasoftware.exception.MissingSecurityTokenException;
import uk.co.corasoftware.model.security.ApiToken;
import uk.co.corasoftware.service.Service;
import uk.co.corasoftware.service.security.SecurityTokenServiceImpl;
import uk.co.corasoftware.util.security.jwt.JwtTokenDecoder;

public class SecurityTokenController extends Controller<ApiToken> {

	@Autowired
	private Service<ApiToken> securityTokenService;

	public Optional<ApiToken> findByIssuedTo(String name) {
		return ((SecurityTokenServiceImpl) securityTokenService).findByIssuedTo(name);
	}

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
