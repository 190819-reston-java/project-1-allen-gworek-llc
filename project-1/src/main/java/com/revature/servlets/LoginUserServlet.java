package com.revature.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.Employee;
import com.revature.repository.DatabaseConnection;
import com.revature.service.ObjectToJSON;
import com.revature.service.QueryProcessor;

public class LoginUserServlet extends HttpServlet {

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String targetEmail = req.getParameter("email");
		String targetPassword = req.getParameter("userPassword");
		
		String queryToGetUser = QueryProcessor.createSelectQuery("email", targetEmail, "userPassword", targetPassword);
		queryToGetUser = QueryProcessor.specifyTable(queryToGetUser, "employees");
		
		System.out.println(queryToGetUser);
		
		DatabaseConnection dbc = new DatabaseConnection();
		
		ResultSet queryResults = dbc.executeQueryInDatabase(queryToGetUser);
		
		
		try {
			if(queryResults.next()) {
				Employee loggedInEmployee = QueryProcessor.createEmployeeFromQueryResults(queryResults);
				String JSONOfLoggedInEmployee = ObjectToJSON.convertObjectToJSON(loggedInEmployee);
				req.getSession().setAttribute("currentUser", JSONOfLoggedInEmployee);
				resp.sendRedirect("/project-1/homepage.html");
			}
			else {
				resp.sendRedirect("/project-1/loginpage.html");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
