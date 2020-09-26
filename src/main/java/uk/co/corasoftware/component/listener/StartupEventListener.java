package uk.co.corasoftware.component.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import uk.co.corasoftware.controller.security.SecurityTokenController;
import uk.co.corasoftware.enums.ApiType;
import uk.co.corasoftware.enums.InstanceType;
import uk.co.corasoftware.enums.NodeType;
import uk.co.corasoftware.enums.ScalingMode;
import uk.co.corasoftware.enums.TokenType;
import uk.co.corasoftware.model.ServiceProduct;
import uk.co.corasoftware.model.security.ApiToken;
import uk.co.corasoftware.model.server.Environment;
import uk.co.corasoftware.model.server.Node;
import uk.co.corasoftware.repo.ServiceProductRepo;
import uk.co.corasoftware.repo.server.EnvironmentRepo;
import uk.co.corasoftware.repo.server.NodeRepo;
import uk.co.corasoftware.util.security.jwt.JwtTokenEncoder;

@Component
public class StartupEventListener implements ApplicationListener<ContextRefreshedEvent> {

	private static final Logger LOG = LoggerFactory.getLogger(StartupEventListener.class);

	@Autowired
	private SecurityTokenController securityTokenController;

	@Autowired
	private ServiceProductRepo serviceProductRepo;

	@Autowired
	private EnvironmentRepo environmentRepo;

	@Autowired
	private NodeRepo nodeRepo;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		List<ApiToken> tokens = securityTokenController.findAll();
		if (tokens == null || tokens.isEmpty()) {

			LOG.info("No root token detected. Generating token...");
			// @formatter:off
			ApiToken token = ApiToken.builder()
					.name("ROOT_TOKEN")
					.issuedBy("ROOT")
					.issuedTo("ROOT")
					.description("Root Token")
					.token(JwtTokenEncoder.createJWT("ROOT", "ROOT", "ROOT Token", 0))
					.tokenType(TokenType.PRODUCTION)
					.build();
			// @formatter:on
			securityTokenController.save(token);
			LOG.info("Root token generated");

			/*
			 * DEBUG
			 */
			generateTestData();
		}
	}

	private void generateTestData() {
		Environment environment = Environment.builder().shortDomain("test-domain").region("LON-1").isHaEnabled(false)
				.sslState(false).build();

		List<Node> nodes = new ArrayList();
		for (int i = 1; i <= 5; i++) {
			Node node = Node.builder().displayName("node-" + UUID.randomUUID().toString()).nodeType(NodeType.NODEJS)
					.nodeGroup("cp" + i).diskLimit(10).extip(0).extipv6(0).fixedCloudlets(1).flexibleCloudlets(4)
					.restartDelay(30).tag("tag-" + i).scalingMode(ScalingMode.STATEFUL).build();
			nodes.add(node);
		}

		nodeRepo.saveAll(nodes);
		environment = environmentRepo.save(environment);

		ServiceProduct service = ServiceProduct.builder().name("Test Product").description("Test Product Description")
				.environment(environment).instanceType(InstanceType.SINGLE_APPLICATION_SERVER).apiType(ApiType.JELASTIC)
				.nodes(nodes).build();

		service = serviceProductRepo.save(service);
	}
}
