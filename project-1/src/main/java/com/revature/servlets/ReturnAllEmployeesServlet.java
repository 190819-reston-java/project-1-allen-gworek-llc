package com.revature.servlets;

import java.io.IOException;
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

public class ReturnAllEmployeesServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		DatabaseConnection dbc = new DatabaseConnection();

		try {
			ResultSet allEmployees = dbc.executeQueryInDatabase("SELECT * FROM employees");
			EmployeeList allEmployeesFormattedList = new EmployeeList();
			
			ResultSetMetaData newMetaData = allEmployees.getMetaData();
			ArrayList<String> columnNames = new ArrayList<String>();
			
			for(int columnIndex = 0; columnIndex < newMetaData.getColumnCount(); columnIndex++) {
				String currentColumnIndexName;
				currentColumnIndexName = newMetaData.getColumnName(columnIndex);
				columnNames.add(currentColumnIndexName);
			}
			
			while (allEmployees.next()) {

				ArrayList<Object> currentColumnValues = new ArrayList<Object>();
				for(int columnIndex = 0; columnIndex < newMetaData.getColumnCount(); columnIndex++) {
					currentColumnValues.add(allEmployees.getObject(columnIndex));
				}
				
				Employee newEmployee = new Employee();
				
				Method[] allObjectMethods = newEmployee.getClass().getDeclaredMethods();
				
				for(Method currentMethod : allObjectMethods) {
					if (currentMethod.isAnnotationPresent(Setter.class)) {
						
					}
				}
				
				allEmployeesFormattedList.add(newEmployee);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
