package uk.co.corasoftware.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uk.co.corasoftware.exception.InvalidSecurityTokenException;
import uk.co.corasoftware.model.ServiceProduct;
import uk.co.corasoftware.repo.ServiceProductRepo;
import uk.co.corasoftware.repo.server.EnvironmentRepo;
import uk.co.corasoftware.repo.server.NodeRepo;

@RestController
//@Profile("dev")
public class TestApi {

	@Autowired
	private ServiceProductRepo serviceProductRepo;

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
		
		ServiceProduct product = serviceProductRepo.findAll().get(0);
		
		return new ResponseEntity<ServiceProduct>(product, HttpStatus.OK);
		// @formatter:on
	}
}
