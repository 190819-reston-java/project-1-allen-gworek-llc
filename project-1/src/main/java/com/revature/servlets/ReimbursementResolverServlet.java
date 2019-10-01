package com.revature.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.exception.UnmatchableTypesException;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.repository.DatabaseConnection;
import com.revature.service.JSONToObject;
import com.revature.service.QueryProcessor;

public class ReimbursementResolverServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("In Reimbursement Resolver Servlet");
		
		Employee currentManager = JSONToObject.convertEmployeeJSONToObject((String)req.getSession().getAttribute("currentUser"));
		
		System.out.println("Created current employee");
		int managerID = currentManager.getId();
		
		Enumeration<String>allParams = req.getParameterNames();
		while(allParams.hasMoreElements()) {
			System.out.println(
					allParams.nextElement());
		}
		String resolveAction = req.getParameter("updatereimbursement");
		String[] resolveActionFormat = resolveAction.split(" ");
		
		for(String action : resolveActionFormat) {
			System.out.println(action);
		}
		
		int targetReimbursementID = Integer.valueOf(resolveActionFormat[0]);
		
		
		Employee targetEmployee = new Employee();
		Reimbursement updateReimbursement = new Reimbursement();
		Reimbursement reimbursementToSearchFor = new Reimbursement();

		DatabaseConnection dbc = new DatabaseConnection();

		try {
			ResultSet targetReimbursementResults = dbc
					.executeQueryInDatabase("SELECT * FROM reimbursements WHERE id = " + targetReimbursementID);
			targetReimbursementResults.next();

			updateReimbursement = QueryProcessor.createReimbursementFromQueryResults(targetReimbursementResults);
			reimbursementToSearchFor = QueryProcessor.createReimbursementFromQueryResults(targetReimbursementResults);

			ResultSet targetEmployeeResults = dbc.executeQueryInDatabase(
					"SELECT * FROM employees WHERE id = " + updateReimbursement.getRequestedBy());

			targetEmployeeResults.next();

			targetEmployee = QueryProcessor.createEmployeeFromQueryResults(targetEmployeeResults);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		boolean approvalStatus;
		if (resolveActionFormat[1].equals("True")) {
			approvalStatus = true;
		} else {
			approvalStatus = false;
		}

		updateReimbursement.setResolvedBy(managerID);
		updateReimbursement.setApproved(approvalStatus);

		String updateQuery;
		try {
			updateQuery = QueryProcessor.createUpdateToMatchOther(reimbursementToSearchFor, updateReimbursement);
			updateQuery = QueryProcessor.specifyTable(updateQuery, "reimbursements");

			System.out.println(updateQuery);
			dbc.executeQueryInDatabase(updateQuery);
		} catch (UnmatchableTypesException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Properties props = System.getProperties();

		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");

		final String username = "revatureallengworekllc@gmail.com";
		final String password = "dummyPassword";

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("revatureallengworekllc@gmail.com"));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(targetEmployee.getEmail()));
			message.setSubject("Your reimbursement request has been resolved!");
			message.setText("Hello, " + targetEmployee.getFullName() + ",\n"
					+ "One of your reimbursements has been resolved! Please check the website for more details!" + "\n"
					+ "Allen-Gworek LLC.");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		resp.sendRedirect("/project-1/manager-pending-reimbursements.html");

	}
}
