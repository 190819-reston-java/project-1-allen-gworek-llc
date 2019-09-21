package com.revature.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.model.Employee;
import com.revature.model.EmployeeList;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementList;

public class serviceLayerTests {

	static EmployeeList testEmployeeList;
	static ReimbursementList testReimbursementList;
	
	@Before
	public void setUp() throws Exception {
		
		Employee newEmployee = new Employee(1, "Test Employee 1", "Employee", "New Address", "My Email", "This Password", false);
		Employee newEmployee2 = new Employee(2, "Test Employee 2", "Manager", "New Address", "My Email 2", "This Password 2", true);
		testEmployeeList = new EmployeeList(newEmployee, newEmployee2);
		
		Reimbursement newReimbursement = new Reimbursement(1, "Test Source", 1, 2);
		Reimbursement newReimbursement2 = new Reimbursement(2, "Test Source 2", 3, 2);
		
		testReimbursementList = new ReimbursementList(newReimbursement, newReimbursement2);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	// Update Employee Information Test
	// Should, for a given Employee, update their information
	
	// Update Reimbursement Information Test
	// Should, for a given Reimbursement, update its information
	
	// Find All Employees Query Test
	// Should, for a list of employees, return a query for all employees
	
	// Find Specific Employee Test
	// Should, for a list of employees, return a specific employee
	
	// Find Specific Employee Query Test
	// Should, for a given employee, return an appropriate query for that employee
	@Test
	public void findSpecificEmployeeQueryTest() throws IllegalArgumentException, IllegalAccessException {
		String testQueryString = "SELECT * "
								+ "FROM tableName "
								+ "WHERE id = 1, fullName = 'Test Employee 1', jobTitle = 'Employee', "
								+ "address = 'New Address', email = 'My Email', userPassword = 'This Password', "
								+ "managerStatus = false;";
		assertEquals(testQueryString, QueryProcessor.createSelectQuery(testEmployeeList.get(0)));
	}
	
	// Find Specific Reimbursement Test
	// Should, for a list of reimbursements, return a specific reimbursement

	// Find Specific Reimbursement Query Test
	// Should, for a given reimbursement, return a query for that reimbursement
	@Test
	public void findSpecificReimbursementQueryTest() throws IllegalArgumentException, IllegalAccessException {
		String testQueryString = "SELECT * "
				+ "FROM tableName "
				+ "WHERE id = 1, reimbursementSource = 'Test Source', requestedBy = 1, "
				+ "resolvedBy = 2;";
		
		assertEquals(testQueryString, QueryProcessor.createSelectQuery(testReimbursementList.get(0)));
	}
	
	// Find All Specified Employees Test
	// Should, for all employees, return a list of employees that match given criteria
	
	// Find All Specified Employees Query Test
	// Should, for a given criteria, return a query that will return a list of employees that match the given criteria
	
	@Test public void findAllSpecifiedEmployeesQueryTest() {
		String testQueryString = "SELECT * "
								+ "FROM tableName "
								+ "WHERE address = 'New Address', id = 1;";
		assertEquals(testQueryString, QueryProcessor.createSelectQuery("address", "New Address", "id", 1));
	}

	// Find All Specified Reimbursements Test
	// Should, for all reimbursements, return a list of employees that match given criteria
	
	// Find All Specified Reimbursements Query Test
	// Should, for all reimbursements, return a query that will return a list of reimbursements that match the given criteria
	@Test public void findAllSpecifiedReimbursementsQueryTest() {
		String testQueryString = "SELECT * "
								+ "FROM tableName "
								+ "WHERE resolvedBy = 2;";
		
		assertEquals(testQueryString, QueryProcessor.createSelectQuery("resolvedBy", 2));
	}
	
	// Insert Specified Employee Query Test
	// Should, for a given Employee, create a query that will insert that employee into the table
	@Test public void insertSpecifiedEmployeeQueryTest() throws IllegalArgumentException, IllegalAccessException {
		String testQueryString = "INSERT INTO tableName "
								+ "(id, fullName, jobTitle, address, email, userPassword, managerStatus) "
								+ "VALUES (1, 'Test Employee 1', 'Employee', 'New Address', 'My Email', 'This Password', false);";
		assertEquals(testQueryString, QueryProcessor.createInsertQuery(testEmployeeList.get(0)));
	}
	
	@Test public void insertSpecifiedReimbursementQueryTest() throws IllegalArgumentException, IllegalAccessException {
		String testQueryString = "INSERT INTO tableName "
								+ "(id, reimbursementSource, requestedBy, resolvedBy) "
								+ "VALUES (2, 'Test Source 2', 3, 2);";
		assertEquals(testQueryString, QueryProcessor.createInsertQuery(testReimbursementList.get(1)));
	}
}
