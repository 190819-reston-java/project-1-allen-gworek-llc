package com.revature.servlets;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.repository.DatabaseConnection;
import com.revature.service.JSONToObject;
import com.revature.service.QueryProcessor;

public class ReimbursementCreationServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		Employee employeeWhoCreatedRequest = JSONToObject.convertEmployeeJSONToObject((String)req.getSession().getAttribute("currentUser"));
		
		Enumeration<String> passedInParameters = req.getParameterNames();

		Reimbursement createdReimbursement = new Reimbursement();
		String currentAttribute;

		Field currentField;
		
		while (passedInParameters.hasMoreElements()) {
			System.out.println("Iterating through parameters");

			currentAttribute = passedInParameters.nextElement();
			System.out.println(currentAttribute);
			try {
				currentField = createdReimbursement.getClass().getDeclaredField(currentAttribute);
				System.out.println(currentField.getName());

				try {
					currentField.setAccessible(true);
					currentField.set(createdReimbursement, req.getParameter(currentAttribute));
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (NoSuchFieldException e) {
				System.out.println("The field wasn't found!");
				continue;
			}
		}
		
		createdReimbursement.setRequestedBy(employeeWhoCreatedRequest.getId());
		createdReimbursement.setRequestedByName(employeeWhoCreatedRequest.getFullName());
		
		DatabaseConnection dbc = new DatabaseConnection();
		
		String queryToInsertReimbursement;
		try {
			queryToInsertReimbursement = QueryProcessor.createInsertQuery(createdReimbursement);
			queryToInsertReimbursement = QueryProcessor.specifyTable(queryToInsertReimbursement, "reimbursements");
			
			dbc.executeQueryInDatabase(queryToInsertReimbursement);
			System.out.println(queryToInsertReimbursement);
			resp.sendRedirect("/project-1/app/submit-reimbursement-image.html");
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
