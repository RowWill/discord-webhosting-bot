package uk.co.corasoftware.controller.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uk.co.corasoftware.enums.ApiType;
import uk.co.corasoftware.enums.RewardType;
import uk.co.corasoftware.enums.TokenType;
import uk.co.corasoftware.exception.InvalidSecurityTokenException;
import uk.co.corasoftware.model.Reward;
import uk.co.corasoftware.security.controller.SecurityTokenController;
import uk.co.corasoftware.security.jwt.util.JwtTokenEncoder;
import uk.co.corasoftware.security.model.ApiToken;

@RestController
@Profile("dev")
public class TestApi {

	@Autowired
	private SecurityTokenController securityTokenController;

	@RequestMapping("/alive")
	public ResponseEntity<String> alive() {
		return new ResponseEntity<String>("alive", HttpStatus.OK);
	}

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

	@RequestMapping({ "api/test-api" })
	public ResponseEntity<Reward> testApi(@RequestParam Optional<String> passphrase)
			throws InvalidSecurityTokenException {
		new Reward();
		// @formatter:off
		return new ResponseEntity<Reward>(Reward.builder()
												.apiType(ApiType.MONGODB)
												.name("TEST_REWARD")
												.description("TEST REWARD")
												.id("TEST-ID")
												.availableAtLevel(20)
												.rewardType(RewardType.SINGLE_DATABASE_INSTANCE)
												.build(), HttpStatus.OK);
		// @formatter:on
	}
}
