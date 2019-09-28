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

public class UpdateEmployeeInformationServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Enumeration<String> passedInParameters = req.getParameterNames();

		Employee currentEmployee = JSONToObject
				.convertEmployeeJSONToObject(((String) req.getSession().getAttribute("currentUser")));

		Employee updatedEmployeeValues = new Employee();
		String currentAttribute;
		Field currentField;

		updatedEmployeeValues.setID(currentEmployee.getID());
		updatedEmployeeValues.setManagerStatus(currentEmployee.isManagerStatus());

		while (passedInParameters.hasMoreElements()) {
			try {
				currentAttribute = passedInParameters.nextElement();
				System.out.println(currentAttribute + " is the name of the current attribute");
				currentField = updatedEmployeeValues.getClass().getDeclaredField(currentAttribute);
				System.out.println(currentField + " is the name of the current field");

				try {
					currentField.setAccessible(true);
					if (req.getParameter(currentAttribute).equals("")) {
						Field otherField = currentEmployee.getClass().getDeclaredField(currentAttribute);
						otherField.setAccessible(true);

						if (otherField.getType().toString().equals("class java.lang.String")) {
							currentField.set(updatedEmployeeValues, (String) otherField.get(currentEmployee));
						}
						if (otherField.getType().toString().equals("int")) {
							currentField.set(updatedEmployeeValues, otherField.getInt(currentEmployee));
						}
						if (otherField.getType().toString().equals("boolean")) {
							currentField.set(updatedEmployeeValues, otherField.getBoolean(currentEmployee));
						}
					} else {
						currentField.set(updatedEmployeeValues, req.getParameter(currentAttribute));
					}
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
		DatabaseConnection dbc = new DatabaseConnection();

		try {
			String updateQuery = QueryProcessor.createUpdateToMatchOther(currentEmployee, updatedEmployeeValues);
			updateQuery = QueryProcessor.specifyTable(updateQuery, "employees");
			System.out.println(updateQuery);
			dbc.executeQueryInDatabase(updateQuery);

			try {
				String retrieveCurrentUserQuery = "SELECT * FROM employees WHERE id = " + currentEmployee.getID() + ";";
				ResultSet currentUserResults = dbc.executeQueryInDatabase(retrieveCurrentUserQuery);
				currentUserResults.next();

				req.getSession().setAttribute("currentUser", ObjectToJSON
						.convertObjectToJSON(QueryProcessor.createEmployeeFromQueryResults(currentUserResults)));

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {

		}

		resp.sendRedirect("/project-1/homepage.html");
	}

}
