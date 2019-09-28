package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
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

public class ViewTargetEmployeeReimbursementsServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DatabaseConnection dbc = new DatabaseConnection();
		
		int valueToSearch = Integer.valueOf(req.getParameter("requestedBy"));
		
		String findReimbursementsByRequestID = QueryProcessor.createSelectQuery("requestedBy", valueToSearch);
		findReimbursementsByRequestID = QueryProcessor.specifyTable(findReimbursementsByRequestID, "reimbursements");
		
		ResultSet allReimbursementsByRequestID =  dbc.executeQueryInDatabase(findReimbursementsByRequestID);
		
		ReimbursementList allReimbursementsList = new ReimbursementList();
		try {
			while(allReimbursementsByRequestID.next()) {
				Reimbursement newReimbursement = QueryProcessor.createReimbursementFromQueryResults(allReimbursementsByRequestID);
				allReimbursementsList.add(newReimbursement);
			}
			
			PrintWriter pw = resp.getWriter();
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
