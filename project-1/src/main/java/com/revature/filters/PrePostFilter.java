package com.revature.filters;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PrePostFilter extends MyGenericFilter {

	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest fixedRequest = (HttpServletRequest) request;
		HttpServletResponse fixedResponse = (HttpServletResponse) response;
		if(fixedRequest.getSession().getAttribute("currentEmployee") == null) {
			fixedResponse.sendRedirect("/project-1/loginpage.html");
		}
		
		chain.doFilter(fixedRequest, fixedResponse);
	}
}
