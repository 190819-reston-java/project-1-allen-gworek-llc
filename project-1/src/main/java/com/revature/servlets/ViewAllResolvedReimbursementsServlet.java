package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.ReimbursementList;
import com.revature.repository.DatabaseConnection;
import com.revature.service.ObjectToJSON;
import com.revature.service.QueryProcessor;

public class ViewAllResolvedReimbursementsServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		DatabaseConnection dbc = new DatabaseConnection();

		String queryForAllResolved = "SELECT * FROM reimbursements WHERE resolvedBy IS NOT NULL ORDER BY id;";

		ResultSet allResolvedReimbursementsResults = dbc.executeQueryInDatabase(queryForAllResolved);

		ReimbursementList allResolvedReimbursementsList = new ReimbursementList();
		try {
			while (allResolvedReimbursementsResults.next()) {
				allResolvedReimbursementsList
						.add(QueryProcessor.createReimbursementFromQueryResults(allResolvedReimbursementsResults));
			}
			
			PrintWriter pw = resp.getWriter();
			
			pw.write(ObjectToJSON.convertObjectToJSON(allResolvedReimbursementsList));
		} catch (IllegalArgumentException | IllegalAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
