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

public class ViewResolvedReimbursementsServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DatabaseConnection dbc = new DatabaseConnection();	
		
		Employee currentEmployee = JSONToObject.convertEmployeeJSONToObject((String)req.getSession().getAttribute("currentUser"));
		String employeeID = String.valueOf(currentEmployee.getID());

		String getResolvedReimbursementsForEmployee = "SELECT * FROM reimbursements WHERE idSearch AND resolvedBy IS NOT NULL;";
		getResolvedReimbursementsForEmployee = getResolvedReimbursementsForEmployee.replaceAll("idSearch", "requestedBy = " + employeeID);
		
		ResultSet resolvedReimbursements = dbc.executeQueryInDatabase(getResolvedReimbursementsForEmployee);
		
		PrintWriter pw = resp.getWriter();
		
		ReimbursementList allReimbursementsList = new ReimbursementList();
		try {
			while(resolvedReimbursements.next()) {
				Reimbursement newReimbursement = QueryProcessor.createReimbursementFromQueryResults(resolvedReimbursements);
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
