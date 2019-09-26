package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.Reimbursement;
import com.revature.repository.DatabaseConnection;
import com.revature.service.JSONToObject;
import com.revature.service.QueryProcessor;

public class ReimbursementCreationServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String JSONFromForm = req.getParameter("reimbursementCreationJSON");
		int employeeWhoCreatedRequest = Integer.valueOf(req.getParameter("employeeID"));
		
		Reimbursement createdReimbursement = JSONToObject.convertReimbursementJSONtoObject(JSONFromForm);
		createdReimbursement.setRequestedBy(employeeWhoCreatedRequest);
		
		DatabaseConnection dbc = new DatabaseConnection();
		
		String queryToInsertReimbursement;
		try {
			queryToInsertReimbursement = QueryProcessor.createInsertQuery(createdReimbursement);
			queryToInsertReimbursement = QueryProcessor.specifyTable(queryToInsertReimbursement, "reimbursements");
			
			dbc.executeQueryInDatabase(queryToInsertReimbursement);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
