package uk.co.corasoftware.component.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import uk.co.corasoftware.controller.application.SecurityTokenController;

public class RequestInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private SecurityTokenController securityTokenController;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean isValid = securityTokenController.isValidToken(request.getHeader("token"),
				request.getParameter("passphrase"));

		return isValid;
	}
}
