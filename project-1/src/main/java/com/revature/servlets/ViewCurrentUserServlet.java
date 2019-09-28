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
import com.revature.repository.DatabaseConnection;
import com.revature.service.ObjectToJSON;
import com.revature.service.QueryProcessor;

public class ViewCurrentUserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PrintWriter pw = resp.getWriter();
		
		String currentUser = (String)req.getSession().getAttribute("currentUser");

		pw.write(currentUser);
	}
}
