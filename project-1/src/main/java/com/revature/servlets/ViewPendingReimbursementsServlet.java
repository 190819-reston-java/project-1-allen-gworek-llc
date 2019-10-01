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
import com.revature.service.JSONToObject;
import com.revature.service.ObjectToJSON;
import com.revature.service.QueryProcessor;

public class ViewPendingReimbursementsServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		DatabaseConnection dbc = new DatabaseConnection();
		
		Employee currentEmployee = JSONToObject.convertEmployeeJSONToObject((String)req.getSession().getAttribute("currentUser"));
		String employeeID = String.valueOf(currentEmployee.getId());

		String findEmployeePendingReimbursements = "SELECT * FROM reimbursements WHERE requestedBy = employeeID AND resolvedBy IS NULL ORDER BY ID;";
		findEmployeePendingReimbursements = findEmployeePendingReimbursements.replaceAll("employeeID", employeeID);
		
		ResultSet pendingReimbursements = dbc.executeQueryInDatabase(findEmployeePendingReimbursements);

		PrintWriter pw = resp.getWriter();

		ReimbursementList allReimbursementsList = new ReimbursementList();
		try {
			while(pendingReimbursements.next()) {
				Reimbursement newReimbursement = QueryProcessor.createReimbursementFromQueryResults(pendingReimbursements);
				allReimbursementsList.add(newReimbursement);
			}
			pw.write(ObjectToJSON.convertObjectToJSON(allReimbursementsList));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
