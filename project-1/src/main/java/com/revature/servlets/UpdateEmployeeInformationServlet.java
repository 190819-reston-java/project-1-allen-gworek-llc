package com.revature.servlets;

import java.io.IOException;

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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String passedInEmployeeJSON = req.getParameter("passedInEmployeeJSON");
		Employee updatedEmployeeInfo = JSONToObject.convertEmployeeJSONToObject(passedInEmployeeJSON);
		
		DatabaseConnection dbc = new DatabaseConnection();
		
		try {
			String updateQuery = QueryProcessor.createUpdateQuery(updatedEmployeeInfo);
			updateQuery = QueryProcessor.specifyUpdateConditions(updateQuery, "id", updatedEmployeeInfo.getID());
			updateQuery = QueryProcessor.specifyTable(updateQuery, "employees");
			
			dbc.executeQueryInDatabase(updateQuery);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
