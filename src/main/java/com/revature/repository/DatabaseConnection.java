package com.revature.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {

	public ResultSet executeQueryInDatabase(String queryToExecute) {
		Statement statement = null;
		ResultSet results = null;
		Connection conn = null;
		
		try {
			conn = ConnectionUtil.getConnection();
			
			statement = conn.createStatement();
			
			if (queryToExecute.startsWith("SELECT")) {
				results = statement.executeQuery(queryToExecute);
			}
			else {
				statement.executeUpdate(queryToExecute);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(results != null) {
					results.close();
				}
				statement.close();
				conn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return results;
	}
}
