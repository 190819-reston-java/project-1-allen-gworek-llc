package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.exception.UnmatchableTypesException;
import com.revature.model.Reimbursement;
import com.revature.repository.DatabaseConnection;
import com.revature.service.JSONToObject;
import com.revature.service.QueryProcessor;

public class ReimbursementResolverServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String JSONOfTargetReimbursement = req.getParameter("targetReimbursementJSON");
		int managerID = Integer.valueOf(req.getParameter("employeeID"));
		String resolveAction = req.getParameter("resolveAction");
		
		Reimbursement updateReimbursement = JSONToObject.convertReimbursementJSONtoObject(JSONOfTargetReimbursement);
		Reimbursement reimbursementToSearchFor = JSONToObject.convertReimbursementJSONtoObject(JSONOfTargetReimbursement);
		
		updateReimbursement.setResolvedBy(managerID);
		
		String updateQuery;
		try {
			updateQuery = QueryProcessor.createUpdateToMatchOther(reimbursementToSearchFor, updateReimbursement);
			updateQuery = QueryProcessor.specifyTable(updateQuery, "reimbursements");
			
			DatabaseConnection dbc = new DatabaseConnection();
			
			dbc.executeQueryInDatabase(updateQuery);
		} catch (UnmatchableTypesException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
