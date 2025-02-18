package uk.co.corasoftware.config.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import uk.co.corasoftware.component.interceptor.RequestInterceptor;

@Component
public class InterceptorConfig implements WebMvcConfigurer {

	@Autowired
	private RequestInterceptor requestInterceptor;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// @formatter:off
		registry.addInterceptor(requestInterceptor)
					.addPathPatterns("/api/**")
					.excludePathPatterns("generate_test_token/**", "h2-console/**",
							"swagger-ui.html", "alive");
		// @formatter:on
	}
}
