package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.lang.reflect.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.Employee;
import com.revature.model.EmployeeList;
import com.revature.repository.DatabaseConnection;
import com.revature.service.ObjectToJSON;
import com.revature.service.QueryProcessor;

public class ReturnAllEmployeesServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		DatabaseConnection dbc = new DatabaseConnection();

		ResultSet allEmployees = dbc.executeQueryInDatabase("SELECT * FROM employees ORDER BY id;");
		
		EmployeeList allEmployeeList = new EmployeeList();
			
			try {
				while(allEmployees.next()) {
				allEmployeeList.add(QueryProcessor.createEmployeeFromQueryResults(allEmployees));
				}
				PrintWriter pw = resp.getWriter();
				pw.write(ObjectToJSON.convertObjectToJSON(allEmployeeList));
			} catch (IllegalArgumentException | IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

		}
	}

}
