package com.revature.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementList;
import com.revature.repository.DatabaseConnection;
import com.revature.service.ObjectToJSON;
import com.revature.service.QueryProcessor;

public class ViewAllReimbursementsServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		DatabaseConnection dbc = new DatabaseConnection();

		ResultSet allReimbursements = dbc.executeQueryInDatabase("SELECT * FROM reimbursements;");

		ReimbursementList allReimbursementsList = new ReimbursementList();

		try {
			while (allReimbursements.next()) {
				allReimbursementsList.add(QueryProcessor.createReimbursementFromQueryResults(allReimbursements));
			}
			resp.getWriter().write(ObjectToJSON.convertObjectToJSON(allReimbursementsList));

		} catch (IllegalArgumentException | IllegalAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
