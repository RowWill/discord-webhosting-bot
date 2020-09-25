package uk.co.corasoftware.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
				http
					.headers().frameOptions().sameOrigin()
			        .and()
					.csrf().ignoringAntMatchers("/h2-console/**")
					.and()
					.authorizeRequests()
						.antMatchers("/h2-console/**", "/**")
						.permitAll()
					.anyRequest().authenticated();
				// @formatter:on
	}

	@Bean
	public RestOperations restOperations() {
		return new RestTemplate();
	}
}