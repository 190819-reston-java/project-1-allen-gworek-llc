package com.revature.servlets;

import java.io.IOException;
import java.lang.reflect.Field;
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

public class UpdateEmployeeInformationServlet extends HttpServlet {

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String passedInEmployeeJSON = req.getParameter("passedInEmployeeJSON");
		Employee updatedEmployeeInfo = JSONToObject.convertEmployeeJSONToObject(passedInEmployeeJSON);

		DatabaseConnection dbc = new DatabaseConnection();

		try {
			ResultSet existingEmployeeResults = dbc.executeQueryInDatabase(
					"SELECT * FROM employees WHERE id = " + Integer.toString(updatedEmployeeInfo.getID()) + ";");
			
			existingEmployeeResults.next();

			Employee retrievedEmployee = QueryProcessor.createEmployeeFromQueryResults(existingEmployeeResults);
			
			String updateQuery = QueryProcessor.createUpdateToMatchOther(retrievedEmployee, updatedEmployeeInfo);
			updateQuery = QueryProcessor.specifyTable(updateQuery, "employees");

			dbc.executeQueryInDatabase(updateQuery);
		} catch (IllegalArgumentException | IllegalAccessException | SQLException e) {
			
		}
	}

}
