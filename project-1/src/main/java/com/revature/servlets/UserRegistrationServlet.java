package com.revature.servlets;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.Employee;
import com.revature.repository.DatabaseConnection;
import com.revature.service.JSONToObject;
import com.revature.service.ObjectToJSON;
import com.revature.service.QueryProcessor;

public class UserRegistrationServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// User form is created and this runs

		Enumeration<String> passedInParameters = req.getParameterNames();

		Employee employeeToInsert = new Employee();
		String currentAttribute;

		Field currentField;

		while (passedInParameters.hasMoreElements()) {
			try {
				currentAttribute = passedInParameters.nextElement();
				currentField = employeeToInsert.getClass().getDeclaredField(currentAttribute);

				try {
					currentField.setAccessible(true);
					currentField.set(employeeToInsert, req.getParameter(currentAttribute));
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (NoSuchFieldException e) {
				continue;
			}
		}

		String userEmailToVerify = employeeToInsert.getEmail();

		String verifyNotExists = QueryProcessor.createSelectQuery("email", userEmailToVerify);
		verifyNotExists = QueryProcessor.specifyTable(verifyNotExists, "employees");

		DatabaseConnection dbc = new DatabaseConnection();

		ResultSet verifyResults = dbc.executeQueryInDatabase(verifyNotExists);

		try {
			if (verifyResults.next()) {
				System.out.println("Email already exists!");
				resp.sendRedirect("/project-1/login/signuppage.html");
			} else {
				String createUserInTable;
				try {
					createUserInTable = QueryProcessor.createInsertQuery(employeeToInsert);
					createUserInTable = QueryProcessor.specifyTable(createUserInTable, "employees");

					dbc.executeQueryInDatabase(createUserInTable);

					String retrieveCurrentUserQuery = "SELECT * FROM employees WHERE email = '"
							+ employeeToInsert.getEmail() + "';";
					ResultSet currentUserResults = dbc.executeQueryInDatabase(retrieveCurrentUserQuery);
					currentUserResults.next();

					req.getSession().setAttribute("currentUser", ObjectToJSON
							.convertObjectToJSON(QueryProcessor.createEmployeeFromQueryResults(currentUserResults)));

					resp.sendRedirect("/project-1/app/homepage.html");
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
