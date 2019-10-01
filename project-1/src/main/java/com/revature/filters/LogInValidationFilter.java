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
		System.out.println(fixedRequest.getSession().getAttribute("currentEmployee"));
		
		if (fixedRequest.getSession().getAttribute("currentEmployee") == null) {
			for (String urlPiece : currentURLArrayList) {
				System.out.print(urlPiece + "/");
				System.out.println();
			}
			System.out.println("There is no user in this session!");
			if (currentURLArrayList.contains("signuppage.html")) {
				System.out.println("On the signup page!");
			}

			if (currentURLArrayList.contains("loginpage.html")) {
				System.out.println("On the login page!");
			}
			if (!currentURLArrayList.contains("signuppage.html") & !currentURLArrayList.contains("loginpage.html")) {
				fixedResponse.sendRedirect("/project-1/loginpage.html");
			}
		}

		chain.doFilter(request, response);

	}
}
