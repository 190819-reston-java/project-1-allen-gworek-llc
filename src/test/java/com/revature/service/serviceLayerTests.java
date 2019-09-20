package com.revature.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.model.EmployeeList;

public class serviceLayerTests {

	static EmployeeList testEmployeeList;
	
	@Before
	public void setUp() throws Exception {
		
		testEmployeeList = new EmployeeList();
		
	}

	@After
	public void tearDown() throws Exception {
	}

	// Update Employee Information Test
	// Should, for a given Employee, update their information
	
	// Update Reimbursement Information Test
	// Should, for a given Reimbursement, update its information
	
	// Find Specific Employee Test
	// Should, for a list of employees, return a specific employee
	
	// Find Specific Reimbursement Test
	// Should, for a list of reimbursements, return a specific reimbursement
	
	// Find All Specified Employees Test
	// Should, for all employees, return a list of employees that match given criteria
	
	// Find All Specified Reimbursements Test
	// Should, for all reimbursements, return a list of employees that match given criteria
}
