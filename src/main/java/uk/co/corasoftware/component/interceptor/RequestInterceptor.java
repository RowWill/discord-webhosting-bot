package uk.co.corasoftware.component.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import uk.co.corasoftware.controller.service.security.SecurityTokenController;

public class RequestInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOG = LoggerFactory.getLogger(RequestInterceptor.class);

	@Autowired
	private SecurityTokenController securityTokenController;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOG.debug("Request intercepted: {}", request);
		return securityTokenController.isValidToken(request.getParameter("token"));
	}
}
