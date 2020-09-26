package uk.co.corasoftware.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uk.co.corasoftware.controller.security.SecurityTokenController;
import uk.co.corasoftware.enums.ApiType;
import uk.co.corasoftware.enums.InstanceType;
import uk.co.corasoftware.enums.NodeType;
import uk.co.corasoftware.enums.ScalingMode;
import uk.co.corasoftware.exception.InvalidSecurityTokenException;
import uk.co.corasoftware.model.ServiceProduct;
import uk.co.corasoftware.model.server.Environment;
import uk.co.corasoftware.model.server.Node;
import uk.co.corasoftware.repo.IssuedServiceRepo;
import uk.co.corasoftware.repo.ServiceProductRepo;
import uk.co.corasoftware.repo.server.EnvironmentRepo;
import uk.co.corasoftware.repo.server.NodeRepo;

@RestController
//@Profile("dev")
public class TestApi {

	@Autowired
	private SecurityTokenController securityTokenController;

	@Autowired
	private ServiceProductRepo serviceProductRepo;

	@Autowired
	private IssuedServiceRepo issuedServiceRepo;

	@Autowired
	private EnvironmentRepo environmentRepo;

	@Autowired
	private NodeRepo nodeRepo;

	@Value("#{environment.BOT_DEV_PASSWORD}")
	private String apiDevPassword;

	@Value("#{environment.BOT_PROD_PASSWORD}")
	private String apiProdPassword;

	@RequestMapping(path = "/alive", method = RequestMethod.GET)
	public ResponseEntity<String> alive() {
		return new ResponseEntity<String>("alive", HttpStatus.OK);
	}

	@RequestMapping(path = { "api/test-api" }, method = RequestMethod.GET)
	public ResponseEntity<ServiceProduct> testApi(@RequestParam String token) throws InvalidSecurityTokenException {
		// @formatter:off
		
		Environment environment = Environment.builder()
				.shortDomain("test-domain")
				.region("LON-1")
				.isHaEnabled(false)
				.sslState(false)
				.build();
		
		List<Node> nodes = new ArrayList();
		for(int i = 1; i <= 5; i++) {
			Node node = Node.builder()
					.displayName("node-" + UUID.randomUUID().toString())
					.nodeType(NodeType.NODEJS)
					.nodeGroup("cp" + i)
					.diskLimit(10)
					.extip(0)
					.extipv6(0)
					.fixedCloudlets(1)
					.flexibleCloudlets(4)
					.restartDelay(30)
					.tag("tag-" + i)
					.scalingMode(ScalingMode.STATEFUL)
					.build();
			nodes.add(node);
		}
		
		nodeRepo.saveAll(nodes);
		environment = environmentRepo.save(environment);
		
		ServiceProduct service = ServiceProduct.builder()
				.name("Test Product")
				.description("Test Product Description")
				.environment(environment)
				.instanceType(InstanceType.SINGLE_APPLICATION_SERVER)
				.apiType(ApiType.JELASTIC)
				.nodes(nodes)
				.build();
		
		service = serviceProductRepo.save(service);
		
		return new ResponseEntity<ServiceProduct>(service, HttpStatus.OK);
		// @formatter:on
	}
}
