package com.revature.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public class DatabaseConnection {

	public ResultSet executeQueryInDatabase(String queryToExecute) {
		Statement statement = null;
		ResultSet results = null;
		Connection conn = null;

		try {
			conn = ConnectionUtil.getConnection();

			statement = conn.createStatement();

			if (queryToExecute.startsWith("SELECT")) {
				results = statement.executeQuery(queryToExecute);
			} else {
				statement.executeUpdate(queryToExecute);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (results != null) {
					results.close();
				}
				statement.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return results;
	}

	public Employee getEmployee(String queryToExecute) {

		ResultSet results = executeQueryInDatabase(queryToExecute);

		try {
			if (results.first()) {
				int employeeID = results.getInt("id");
				String employeeName = results.getString("fullName");
				String employeeJobTitle = results.getString("jobTitle");
				String employeeAddress = results.getString("address");
				String employeeEmail = results.getString("email");
				String employeePassword = results.getString("userPassword");
				boolean employeeManagerStatus = results.getBoolean("managerStatus");

				Employee returnEmployee = new Employee(employeeID, employeeName, employeeJobTitle, employeeAddress,
						employeeEmail, employeePassword, employeeManagerStatus);

				return returnEmployee;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				results.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	public Reimbursement getReimbursement(String queryToExecute) {

		ResultSet results = executeQueryInDatabase(queryToExecute);

		try {
			if (results.first()) {
				int reimbursementID = results.getInt("id");
				String reimbursementSource = results.getString("reimbursementSource");
				int requestedBy = results.getInt("requestedBy");
				int resolvedBy = results.getInt("resolvedBy");

				Reimbursement returnReimbursement = new Reimbursement(reimbursementID, reimbursementSource, 
																	  requestedBy, resolvedBy);

				return returnReimbursement;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				results.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;

	}
}
