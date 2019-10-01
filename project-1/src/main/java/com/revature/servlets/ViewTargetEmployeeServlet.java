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

public class ViewTargetEmployeeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DatabaseConnection dbc = new DatabaseConnection();

		String[] thisPath = req.getPathInfo().split("/");
		
		ResultSet targetEmployeeByID = dbc
				.executeQueryInDatabase("SELECT * FROM employees WHERE id = " + thisPath[1]);

		
		try {		
			targetEmployeeByID.next();
			Employee targetEmployee = QueryProcessor.createEmployeeFromQueryResults(targetEmployeeByID);
			
			resp.getWriter().write(ObjectToJSON.convertObjectToJSON(targetEmployee).toString());
		} catch (IllegalArgumentException | IllegalAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
