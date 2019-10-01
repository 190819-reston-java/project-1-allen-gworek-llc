package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementList;
import com.revature.repository.DatabaseConnection;
import com.revature.service.ObjectToJSON;
import com.revature.service.QueryProcessor;

public class ViewTargetEmployeeReimbursementsServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		DatabaseConnection dbc = new DatabaseConnection();

		String[] thisPath = req.getPathInfo().split("/");
		System.out.println(thisPath[1]);

		ResultSet targetEmployeeReimbursementsByID = dbc
				.executeQueryInDatabase("SELECT * FROM reimbursements WHERE requestedBy = " + thisPath[thisPath.length - 1]);

		try {

			ReimbursementList allTargetReimbursements = new ReimbursementList();
			Reimbursement targetReimbursement;
			while (targetEmployeeReimbursementsByID.next()) {
				targetReimbursement = QueryProcessor.createReimbursementFromQueryResults(targetEmployeeReimbursementsByID);
				allTargetReimbursements.add(targetReimbursement);
			}
			resp.getWriter().write(ObjectToJSON.convertObjectToJSON(allTargetReimbursements).toString());
		} catch (IllegalArgumentException | IllegalAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
