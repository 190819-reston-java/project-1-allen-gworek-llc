package com.revature.controller;

import com.revature.model.Employee;
import com.revature.model.EmployeeList;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementList;
import com.revature.repository.DatabaseConnection;
import com.revature.service.QueryProcessor;

public class ExampleDriver {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		
		DatabaseConnection dbc = new DatabaseConnection();
		
		Employee testEmployee1 = new Employee("Chris Allen", "Back-end Developer", "Chris Apartment", "chris@email.com", "194121234", true);
		Employee testEmployee2 = new Employee("Jonathon Gworek", "Front-end Developer", "Jonathons Apartment", "john@email.com", "1234917823", true);
		EmployeeList myEmployeeList = new EmployeeList(testEmployee1, testEmployee2);
		
		Reimbursement testReimbursement1 = new Reimbursement("Chris Fancy Laptop");
		Reimbursement testReimbursement2 = new Reimbursement("Johnathons Guitar Lessons");
		ReimbursementList myReimbursementList = new ReimbursementList(testReimbursement1, testReimbursement2);
		
		String queryToExecute;
		
		for(Employee myEmployee : myEmployeeList) {
			queryToExecute = QueryProcessor.createInsertQuery(myEmployee);
			queryToExecute = QueryProcessor.specifyTable(queryToExecute, "employees");
			dbc.executeQueryInDatabase(queryToExecute);
		}
		
		for(Reimbursement myReimbursement : myReimbursementList) {
			queryToExecute = QueryProcessor.createInsertQuery(myReimbursement);
			queryToExecute = QueryProcessor.specifyTable(queryToExecute, "reimbursements");
			
			dbc.executeQueryInDatabase(queryToExecute);
		}
		
		Employee testEmployee3 = new Employee("Temporary Hire", "Coffee Intern", "Revature Parking Lot", "obvioustemp@email.com", "1234123", false);
		Reimbursement testReimbursement3 = new Reimbursement("Temps Tuition Payment");
		
		queryToExecute = QueryProcessor.createInsertQuery(testEmployee3);
		queryToExecute = QueryProcessor.specifyTable(queryToExecute, "employees");
		
		dbc.executeQueryInDatabase(queryToExecute);
		
		queryToExecute = QueryProcessor.createInsertQuery(testReimbursement3);
		queryToExecute = QueryProcessor.specifyTable(queryToExecute, "reimbursements");
		
		dbc.executeQueryInDatabase(queryToExecute);

		
	}

}
