package uk.co.corasoftware.controller.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uk.co.corasoftware.controller.security.SecurityTokenController;
import uk.co.corasoftware.enums.TokenType;
import uk.co.corasoftware.exception.InvalidSecurityTokenException;
import uk.co.corasoftware.model.ServiceProduct;
import uk.co.corasoftware.model.security.ApiToken;
import uk.co.corasoftware.repo.ServiceProductRepo;
import uk.co.corasoftware.util.security.jwt.JwtTokenEncoder;

//@Profile("dev")
@RestController
public class TestApi {

	@Autowired
	private ServiceProductRepo serviceProductRepo; //TODO controller

	@Autowired
	private SecurityTokenController securityTokenController;

	@Value("#{environment.BOT_DEV_PASSWORD ?: 'password123'}")
	private String apiDevPassword;

	@Value("#{environment.BOT_PROD_PASSWORD}")
	private String apiProdPassword;

	@GetMapping(path = "/alive")
	public ResponseEntity<String> alive() {
		return new ResponseEntity<>("alive", HttpStatus.OK);
	}

	@GetMapping(path = { "api/test-api" })
	public ResponseEntity<ServiceProduct> testApi(@RequestParam String token) {
		return new ResponseEntity<>(serviceProductRepo.findAll().get(0), HttpStatus.OK);
	}

	@RequestMapping({ "/generate_test_token" })
	public ResponseEntity<ApiToken> generateDevToken(
			@RequestParam String password,
			@RequestParam String issuedBy,
			@RequestParam String issuedTo,
			@RequestParam String description,
			@RequestParam TokenType tokenType)
			throws InvalidSecurityTokenException {

		if (!password.equals(apiDevPassword)) {
			throw new InvalidSecurityTokenException("incorrect dev password");
		}

		Optional<ApiToken> tokenOpt = securityTokenController.findByIssuedTo(issuedTo); //TODO generate userIds
		if (tokenOpt.isPresent()) {
			return new ResponseEntity<>(tokenOpt.get(), HttpStatus.OK);
		}

		// @formatter:off
		ApiToken token = ApiToken.builder()
				.name(issuedTo)
				.issuedBy(issuedBy)
				.issuedTo(issuedTo)
				.description(description)
				.tokenType(tokenType) //TODO not null-safe
				.token(JwtTokenEncoder.createJWT(issuedTo, issuedBy, tokenType.name(), 0)) //TODO reference to t is not null-safe
				.build();
		// @formatter:on
		token = securityTokenController.save(token);
		return new ResponseEntity<>(token, HttpStatus.OK);
	}
}
