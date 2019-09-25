package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.Employee;
import com.revature.repository.DatabaseConnection;
import com.revature.service.QueryProcessor;

public class EmployeeObjectHandler extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Employee myEmployee = new Employee("Test Name");
		DatabaseConnection myConnector = new DatabaseConnection();
		
		try {
			String queryToExecute = QueryProcessor.specifyTable(QueryProcessor.createInsertQuery(myEmployee), "employees");
			myConnector.executeQueryInDatabase(queryToExecute);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String employeeID = (String) req.getAttribute("employeeID");
	}
}
