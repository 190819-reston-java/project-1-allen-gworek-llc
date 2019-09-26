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

public class ViewTargetEmployeeReimbursementsServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DatabaseConnection dbc = new DatabaseConnection();
		
		int valueToSearch = Integer.valueOf(req.getParameter("requestedBy"));
		
		String findReimbursementsByRequestID = QueryProcessor.createSelectQuery("requestedBy", valueToSearch);
		findReimbursementsByRequestID = QueryProcessor.specifyTable(findReimbursementsByRequestID, "reimbursements");
		
		ResultSet allReimbursementsByRequestID =  dbc.executeQueryInDatabase(findReimbursementsByRequestID);
		ObjectToJSON.convertObjectToJSON(allReimbursementsByRequestID);
	}
}
