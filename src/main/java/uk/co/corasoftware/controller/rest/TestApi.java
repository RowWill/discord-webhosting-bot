package uk.co.corasoftware.controller.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
//@Profile("dev")
public class TestApi {

	@Autowired
	private SecurityTokenController securityTokenController;

	@Value("#{environment.BOT_DEV_PASSWORD}")
	private String apiDevPassword;

	@RequestMapping(path = "/alive", method = RequestMethod.GET)
	public ResponseEntity<String> alive() {
		return new ResponseEntity<String>("alive", HttpStatus.OK);
	}

	@RequestMapping(path = { "/generate_test_token" }, method = RequestMethod.GET)
	public ResponseEntity<ApiToken> generateDevToken(@RequestParam String password, @RequestParam String issuedBy,
			@RequestParam String issuedTo, @RequestParam String description, @RequestParam String tokenType)
			throws InvalidSecurityTokenException {

		if (!password.equals(apiDevPassword)) {
			throw new InvalidSecurityTokenException("incorrect dev password");
		}

		TokenType t = null;
		if (tokenType.equals("development")) {
			t = TokenType.DEVELOPMENT;
		} else if (tokenType.equals("production")) {
			t = TokenType.PRODUCTION;
		}

		// @formatter:off
		ApiToken token = ApiToken.builder()
				.name(issuedTo + "-api-token")
				.passphrase(issuedTo)
				.issuedBy(issuedBy)
				.issuedTo(issuedTo)
				.description(description)
				.tokenType(t)
				.token(JwtTokenEncoder.createJWT(issuedTo + "-api-token", issuedBy, t.name(), 0))
				.build();
		// @formatter:on
		token = securityTokenController.save(token);
		return new ResponseEntity<ApiToken>(token, HttpStatus.OK);
	}

	@RequestMapping(path = { "api/test-api" }, method = RequestMethod.GET)
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
