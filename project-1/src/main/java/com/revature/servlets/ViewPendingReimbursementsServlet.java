package com.revature.servlets;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.repository.DatabaseConnection;
import com.revature.service.ObjectToJSON;
import com.revature.service.QueryProcessor;

public class ViewPendingReimbursementsServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		DatabaseConnection dbc = new DatabaseConnection();
		String employeeID = req.getParameter("employeeID");

		String findEmployeePendingReimbursements = "SELECT * FROM reimbursements WHERE requestedBy = employeeID AND resolvedBy IS NULL;";
		findEmployeePendingReimbursements = findEmployeePendingReimbursements.replaceAll("employeeID", employeeID);
		
		ResultSet pendingReimbursements = dbc.executeQueryInDatabase(findEmployeePendingReimbursements);

		ObjectToJSON.convertObjectToJSON(pendingReimbursements);
	}
}
