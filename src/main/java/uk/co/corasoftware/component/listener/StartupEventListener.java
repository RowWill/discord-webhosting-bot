package uk.co.corasoftware.component.listener;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

	@Value("#{environment.BOT_PROD_PASSWORD}")
	private String productionPassword;

	@Autowired
	private SecurityTokenController securityTokenController;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		List<ApiToken> tokens = securityTokenController.findAll();
		if (tokens == null || tokens.isEmpty()) {

			LOG.info("No root token detected. Generating token...");
			// @formatter:off
			ApiToken token = ApiToken.builder()
					.name("ROOT_TOKEN")
					.passphrase(productionPassword)
					.issuedBy("Rowan")
					.issuedTo("ROOT")
					.description("Root Token")
					.token(JwtTokenEncoder.createJWT("ROOT", "ROOT", "ROOT Token", 0))
					.tokenType(TokenType.DEVELOPMENT)
					.build();
			// @formatter:on
			securityTokenController.save(token);
			LOG.info("Root token generated");
		}
	}
}
