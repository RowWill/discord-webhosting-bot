package uk.co.corasoftware.config.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import uk.co.corasoftware.component.interceptor.RequestInterceptor;
import uk.co.corasoftware.controller.service.security.SecurityTokenController;
import uk.co.corasoftware.service.security.SecurityTokenService;

@Configuration
public class ApplicationConfig {

	@Bean
	public SecurityTokenService securityTokenService() {
		return new SecurityTokenService();
	}

	@Bean
	public SecurityTokenController securityTokenController() {
		return new SecurityTokenController();
	}

	@Bean
	public RequestInterceptor requestInterceptor() {
		return new RequestInterceptor();
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("uk.co.corasoftware.controller")).paths(PathSelectors.any())
				.build().apiInfo(metaData());
	}

	private ApiInfo metaData() {
		// @formatter:off
		return new ApiInfoBuilder()
				.title("Discord Reward Bot API")
				.description("Backend API Documentation")
				.version("1.0")
				.contact(new Contact("Fluffytme", null, "fluffytme@gmail.com"))
				.build();
		// @formatter:on
	}
}
