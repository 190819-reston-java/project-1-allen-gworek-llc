package com.revature.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogInValidationFilter extends MyGenericFilter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest fixedRequest = (HttpServletRequest) request;
		HttpServletResponse fixedResponse = (HttpServletResponse) response;

		String thisURL = fixedRequest.getRequestURL().toString();
		String[] currentURLArray = thisURL.split("/");
		ArrayList<String> currentURLArrayList = new ArrayList<String>(Arrays.asList(currentURLArray));

		if (fixedRequest.getSession().getAttribute("currentUser") == null) {
			fixedResponse.sendRedirect("/project-1/login/loginpage.html");
			return;
		}
		chain.doFilter(request, response);

	}
}
