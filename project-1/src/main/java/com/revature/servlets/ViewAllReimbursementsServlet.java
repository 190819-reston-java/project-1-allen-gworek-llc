package com.revature.servlets;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.repository.DatabaseConnection;
import com.revature.service.ObjectToJSON;

public class ViewAllReimbursementsServlet extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		DatabaseConnection dbc = new DatabaseConnection();
		
		ResultSet allReimbursements = dbc.executeQueryInDatabase("SELECT * FROM reimbursements;");
		
		ObjectToJSON.convertObjectToJSON(allReimbursements);
	}
}
