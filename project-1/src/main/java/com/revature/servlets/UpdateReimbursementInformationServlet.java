package com.revature.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.Reimbursement;
import com.revature.repository.DatabaseConnection;
import com.revature.service.JSONToObject;
import com.revature.service.QueryProcessor;

public class UpdateReimbursementInformationServlet extends HttpServlet {

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		DatabaseConnection dbc = new DatabaseConnection();
		Reimbursement updatedReimbursement = JSONToObject
				.convertReimbursementJSONtoObject(req.getParameter("reimbursementJSON"));

		String queryToExecute = "SELECT * FROM reimbursements WHERE id = reimbursementID;";
		queryToExecute = queryToExecute.replaceAll("reimbursementID", String.valueOf(updatedReimbursement.getId()));

		try {
			ResultSet existingReimbursementQueryResults = dbc.executeQueryInDatabase(queryToExecute);
			existingReimbursementQueryResults.next();

			Reimbursement existingReimbursement = QueryProcessor
					.createReimbursementFromQueryResults(existingReimbursementQueryResults);

			String updateExistingReimbursementQuery = QueryProcessor.createUpdateToMatchOther(existingReimbursement,
					updatedReimbursement);
			
			updateExistingReimbursementQuery = QueryProcessor.specifyTable(updateExistingReimbursementQuery, "reimbursements");
			dbc.executeQueryInDatabase(updateExistingReimbursementQuery);
		} catch (IllegalArgumentException | IllegalAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
