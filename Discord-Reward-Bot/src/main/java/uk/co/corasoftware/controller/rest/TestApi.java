package uk.co.corasoftware.controller.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uk.co.corasoftware.controller.application.SecurityTokenController;
import uk.co.corasoftware.enums.TokenType;
import uk.co.corasoftware.exception.InvalidSecurityTokenException;
import uk.co.corasoftware.model.Reward;
import uk.co.corasoftware.security.jwt.util.JwtTokenEncoder;
import uk.co.corasoftware.security.model.ApiToken;

@RestController
@Profile("dev")
public class TestApi {

	@Autowired
	private SecurityTokenController securityTokenController;

	@RequestMapping({ "api/generate_test_token" })
	public ResponseEntity<ApiToken> generateDevToken() {
		// @formatter:off
		ApiToken token = ApiToken.builder()
				.name("test-dev-token")
				.issuedBy("Rowan")
				.issuedTo("testuser")
				.description("test api token for development")
				.token(JwtTokenEncoder.createJWT("test-id", "Rowan", "test development token", 0))
				.tokenType(TokenType.DEVELOPMENT)
				.build();
		// @formatter:on
		token = securityTokenController.save(token);
		return new ResponseEntity<ApiToken>(token, HttpStatus.OK);
	}

	@Value("${message:Hello default}")
	private String message;

	@RequestMapping({ "api/test-api" })
	public ResponseEntity<Reward> testApi(@RequestParam Optional<String> passphrase)
			throws InvalidSecurityTokenException {
		return new ResponseEntity<Reward>(new Reward(), HttpStatus.OK);
	}
}
