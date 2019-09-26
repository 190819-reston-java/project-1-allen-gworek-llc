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

public class ViewCurrentUserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		DatabaseConnection dbc = new DatabaseConnection();
		int employeeID = Integer.valueOf(req.getParameter("employeeID"));
		
		String queryToRetrieveEmployeeInfo = QueryProcessor.createSelectQuery("id", employeeID);
		queryToRetrieveEmployeeInfo = QueryProcessor.specifyTable(queryToRetrieveEmployeeInfo, "employees");
		
		ResultSet returnedEmployeeInfo = dbc.executeQueryInDatabase(queryToRetrieveEmployeeInfo);
		
		try {
		returnedEmployeeInfo.next();	
		Employee employeeFromDatabase = QueryProcessor.createEmployeeFromQueryResults(returnedEmployeeInfo);
		
		ObjectToJSON.convertObjectToJSON(employeeFromDatabase);
		
	}
		catch (SQLException e) {
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
