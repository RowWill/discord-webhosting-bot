package uk.co.corasoftware.security.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import uk.co.corasoftware.exception.InvalidSecurityTokenException;
import uk.co.corasoftware.exception.MissingSecurityTokenException;
import uk.co.corasoftware.security.jwt.util.JwtTokenDecoder;
import uk.co.corasoftware.security.model.ApiToken;
import uk.co.corasoftware.security.service.SecurityTokenService;
import uk.co.corasoftware.service.Service;

public class SecurityTokenController implements Service<ApiToken> {

	@Autowired
	private SecurityTokenService securityTokenService;

	public boolean isValidToken(String token) throws MissingSecurityTokenException, InvalidSecurityTokenException {
		if (token == null || token.isEmpty()) {
			throw new MissingSecurityTokenException("Request missing token parameter");
		} else {
			boolean isValidRequest = JwtTokenDecoder.isValidToken(token);
			if (!isValidRequest) {
				throw new InvalidSecurityTokenException("Invalid token");
			}

			return true;
		}
	}

	@Override
	public ApiToken save(ApiToken entity) {
		return securityTokenService.save(entity);
	}

	@Override
	public void deleteAll() {
		securityTokenService.deleteAll();
	}

	@Override
	public void delete(ApiToken entity) {
		securityTokenService.delete(entity);
	}

	@Override
	public void deleteById(String id) {
		securityTokenService.deleteById(id);
	}

	@Override
	public Optional<ApiToken> findById(String id) {
		return securityTokenService.findById(id);
	}

	@Override
	public List<ApiToken> findAll() {
		return securityTokenService.findAll();
	}
}
