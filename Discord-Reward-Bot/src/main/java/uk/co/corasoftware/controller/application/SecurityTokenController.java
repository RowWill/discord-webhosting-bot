package uk.co.corasoftware.controller.application;

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

	public boolean isValidToken(String token, String user)
			throws MissingSecurityTokenException, InvalidSecurityTokenException {
		if (token == null || token.isEmpty()) {
			throw new MissingSecurityTokenException("Request missing token header");
		} else {
			boolean isValidRequest = JwtTokenDecoder.isValidToken(token);
			if (!isValidRequest) {
				throw new InvalidSecurityTokenException("Invalid token");
			} else {
				return tokenMatchesPassphrase(token, user);
			}
		}
	}

	private boolean tokenMatchesPassphrase(String token, String passString)
			throws InvalidSecurityTokenException, MissingSecurityTokenException {
		Optional<ApiToken> apiTokenOptional = securityTokenService.findByToken(token);
		if (apiTokenOptional.isPresent()) {
			ApiToken apiToken = apiTokenOptional.get();
			if (apiToken.getPassphrase().equals(passString)) {
				return true;
			} else {
				throw new InvalidSecurityTokenException("Invalid token");
			}
		} else {
			throw new MissingSecurityTokenException("Token lookup failed");
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
