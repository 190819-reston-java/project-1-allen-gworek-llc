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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		DatabaseConnection dbc = new DatabaseConnection();
		int employeeID = Integer.valueOf(req.getParameter("employeeID"));
		
		String findEmployeePendingReimbursements = QueryProcessor.createSelectQuery("requestedBy", employeeID);
		findEmployeePendingReimbursements = QueryProcessor.specifyTable(findEmployeePendingReimbursements, "reimbursements");
		ResultSet pendingReimbursements = dbc.executeQueryInDatabase(findEmployeePendingReimbursements);
		
		ObjectToJSON.convertObjectToJSON(pendingReimbursements);
	}
}
