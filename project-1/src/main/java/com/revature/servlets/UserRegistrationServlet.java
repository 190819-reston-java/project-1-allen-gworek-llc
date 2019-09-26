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
import com.revature.service.JSONToObject;
import com.revature.service.QueryProcessor;

public class UserRegistrationServlet extends HttpServlet {

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// User form is created and this runs
		
		String JSONFromForm = (String) req.getAttribute("registrationFormJSON");
		Employee employeeToInsert = JSONToObject.convertEmployeeJSONToObject(JSONFromForm);
		
		String userEmailToVerify = employeeToInsert.getEmail();
		
		String verifyNotExists = QueryProcessor.createSelectQuery("email", userEmailToVerify);
		verifyNotExists = QueryProcessor.specifyTable(verifyNotExists, "employees");
		
		DatabaseConnection dbc = new DatabaseConnection();
		
		ResultSet verifyResults = dbc.executeQueryInDatabase(verifyNotExists);
		
		try {
			if(verifyResults.next()) {
				// Redirect user to log-in since there is already a user in the table
			}
			else {
				String createUserInTable;
				try {
					createUserInTable = QueryProcessor.createInsertQuery(employeeToInsert);
					createUserInTable = QueryProcessor.specifyTable(createUserInTable, "employees");
					
					dbc.executeQueryInDatabase(createUserInTable);
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
