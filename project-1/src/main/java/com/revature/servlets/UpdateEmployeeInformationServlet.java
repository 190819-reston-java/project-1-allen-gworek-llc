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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String passedInEmployeeJSON = req.getParameter("passedInEmployeeJSON");
		Employee updatedEmployeeInfo = JSONToObject.convertEmployeeJSONToObject(passedInEmployeeJSON);

		DatabaseConnection dbc = new DatabaseConnection();

		try {
			ResultSet existingEmployeeResults = dbc.executeQueryInDatabase(
					"SELECT * FROM employees WHERE id = " + Integer.toString(updatedEmployeeInfo.getID()) + ";");

			Field[] updatedEmployeeFields = updatedEmployeeInfo.getClass().getFields();
			for (Field currentField : updatedEmployeeFields) {
				currentField.setAccessible(true);
				if (currentField.getType().equals("java.lang.String")) {
					if (currentField.get(updatedEmployeeInfo).equals("")) {
						currentField.set(updatedEmployeeInfo,
								existingEmployeeResults.getString(currentField.getName()));
					}
				}
				if (currentField.getType().equals("int")) {
					if (currentField.get(updatedEmployeeInfo).equals(-1)) {
						currentField.set(updatedEmployeeInfo, existingEmployeeResults.getInt(currentField.getName()));
					}
				}
			}

			String updateQuery = QueryProcessor.createUpdateQuery(updatedEmployeeInfo);
			updateQuery = QueryProcessor.specifyUpdateConditions(updateQuery, "id", updatedEmployeeInfo.getID());
			updateQuery = QueryProcessor.specifyTable(updateQuery, "employees");

			dbc.executeQueryInDatabase(updateQuery);
		} catch (IllegalArgumentException | IllegalAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
