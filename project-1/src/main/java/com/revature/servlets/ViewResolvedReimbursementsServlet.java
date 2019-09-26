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

public class ViewResolvedReimbursementsServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DatabaseConnection dbc = new DatabaseConnection();
		String currentEmployeeID = req.getParameter("currentEmployeeID");
		String getResolvedReimbursementsForEmployee;

		getResolvedReimbursementsForEmployee = "SELECT * FROM reimbursements WHERE idSearch AND resolvedBy IS NOT NULL;";
		getResolvedReimbursementsForEmployee = getResolvedReimbursementsForEmployee.replaceAll("idSearch", "requestedBy = " + currentEmployeeID);
		
		ResultSet resolvedReimbursements = dbc.executeQueryInDatabase(getResolvedReimbursementsForEmployee);
		
		ObjectToJSON.convertObjectToJSON(resolvedReimbursements);
	}
}
