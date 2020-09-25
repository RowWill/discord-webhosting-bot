package uk.co.corasoftware.component.listener;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import uk.co.corasoftware.enums.TokenType;
import uk.co.corasoftware.security.controller.SecurityTokenController;
import uk.co.corasoftware.security.jwt.util.JwtTokenEncoder;
import uk.co.corasoftware.security.model.ApiToken;

@Component
public class StartupEventListener implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger LOG = LoggerFactory.getLogger(StartupEventListener.class);

	@Autowired
	private SecurityTokenController securityTokenController;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		List<ApiToken> tokens = securityTokenController.findAll();
		if (tokens == null || tokens.isEmpty()) {
			// @formatter:off
			ApiToken token = ApiToken.builder()
					.name("test-dev-token")
					.passphrase("test")
					.issuedBy("Rowan")
					.issuedTo("testuser")
					.description("test api token for development")
					.token(JwtTokenEncoder.createJWT("test-id", "Rowan", "test development token", 0))
					.tokenType(TokenType.DEVELOPMENT)
					.build();
			// @formatter:on
			ApiToken t = securityTokenController.save(token);

			LOG.info("#######################################");
			LOG.info("########### DEVELOPMENT TOKEN #########");
			LOG.info("#######################################");
			LOG.info(t.getToken());
			LOG.info("#######################################");
			LOG.info("#######################################");
		}
	}
}
