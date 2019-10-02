package com.revature.service;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.exception.UnmatchableTypesException;
import com.revature.model.Employee;
import com.revature.model.EmployeeList;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementList;

public class serviceLayerTests {

	static EmployeeList testEmployeeList;
	static ReimbursementList testReimbursementList;
	
	@Before
	public void setUp() throws Exception {
		
		Employee newEmployee = new Employee("Test Employee 1", "Employee", "New Address", "My Email", "This Password", false);
		Employee newEmployee2 = new Employee("Test Employee 2", "Manager", "New Address", "My Email 2", "This Password 2", true);
		testEmployeeList = new EmployeeList(newEmployee, newEmployee2);
		
		Reimbursement newReimbursement = new Reimbursement("Test Source", 1, 2);
		Reimbursement newReimbursement2 = new Reimbursement("Test Source 2", 3, 2);
		Reimbursement newReimbursement3 = new Reimbursement("Test Source 3");
		Reimbursement newReimbursement4 = new Reimbursement(1);
		
		testReimbursementList = new ReimbursementList(newReimbursement, newReimbursement2, newReimbursement3, newReimbursement4);
		
	}

	@After
	public void tearDown() throws Exception {
		testEmployeeList = null;
		testReimbursementList = null;
	}

	// Update Employee Information Query With Other Employee Information Test
	// Should, for two employees, create a query that will update the first employee's information with the second employee's
	@Test
	public void updateEmployeeWithOtherEmployeeQueryTest() throws UnmatchableTypesException, IllegalArgumentException, IllegalAccessException {
		String testQueryString = "UPDATE tableName "
							   + "SET id = -1, fullName = 'Test Employee 2', jobTitle = 'Manager', "
							   + "address = 'New Address', email = 'My Email 2', "
							   + "userPassword = 'This Password 2', managerStatus = true "
							   + "WHERE id = -1, fullName = 'Test Employee 1', "
							   + "jobTitle = 'Employee', address = 'New Address', "
							   + "email = 'My Email', userPassword = 'This Password', "
							   + "managerStatus = false;";
		
		assertEquals(testQueryString, QueryProcessor.createUpdateToMatchOther(testEmployeeList.get(0), testEmployeeList.get(1)));
	}
	
	// Update Reimbursement Information With Other Reimbursement Information Query Test
	// Should, for two reimbursements, create a query that will update the first reimbursement's information with the second reimbursement's
	@Test
	public void updateReimbursementWithOtherReimbursementTest() throws UnmatchableTypesException, IllegalArgumentException, IllegalAccessException {
		String testQueryString = "UPDATE tableName "
							   + "SET id = -1, reimbursementSource = 'Test Source 2', "
							   + "requestedBy = 3, resolvedBy = 2 "
							   + "WHERE id = -1, reimbursementSource = 'Test Source', "
							   + "requestedBy = 1, resolvedBy = 2;";
		
		assertEquals(testQueryString, QueryProcessor.createUpdateToMatchOther(testReimbursementList.get(0), testReimbursementList.get(1)));
	}
	
	// Employee Reimbursement Mismatch Handling Test
	// Should, for an employee and a reimbursement, fail to properly create a query that will update one to match the other	
	@Test (expected = UnmatchableTypesException.class)
	public void employeeReimbursementMismatchHandlingTest() throws UnmatchableTypesException, IllegalArgumentException, IllegalAccessException {
		QueryProcessor.createUpdateToMatchOther(testEmployeeList.get(0), testReimbursementList.get(1));
	}
	
	// Find Specific Employee Query Test
	// Should, for a given employee, return an appropriate query for that employee
	@Test
	public void findSpecificEmployeeQueryTest() throws IllegalArgumentException, IllegalAccessException {
		String testQueryString = "SELECT * "
								+ "FROM tableName "
								+ "WHERE id = -1, fullName = 'Test Employee 1', jobTitle = 'Employee', "
								+ "address = 'New Address', email = 'My Email', userPassword = 'This Password', "
								+ "managerStatus = false;";
		assertEquals(testQueryString, QueryProcessor.createSelectQuery(testEmployeeList.get(0)));
	}

	// Find Specific Reimbursement Query Test
	// Should, for a given reimbursement, return a query for that reimbursement
	@Test
	public void findSpecificReimbursementQueryTest() throws IllegalArgumentException, IllegalAccessException {
		String testQueryString = "SELECT * "
				+ "FROM tableName "
				+ "WHERE id = -1, reimbursementSource = 'Test Source', requestedBy = 1, "
				+ "resolvedBy = 2;";
		
		assertEquals(testQueryString, QueryProcessor.createSelectQuery(testReimbursementList.get(0)));
	}

	// Find All Specified Employees Query Test
	// Should, for a given criteria, return a query that will return a list of employees that match the given criteria	
	@Test public void findAllSpecifiedEmployeesQueryTest() {
		String testQueryString = "SELECT * "
								+ "FROM tableName "
								+ "WHERE address = 'New Address', id = 1;";
		assertEquals(testQueryString, QueryProcessor.createSelectQuery("address", "New Address", "id", 1));
	}

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
								+ "(fullName, jobTitle, address, email, userPassword, managerStatus) "
								+ "VALUES ('Test Employee 1', 'Employee', 'New Address', 'My Email', 'This Password', false);";
		assertEquals(testQueryString, QueryProcessor.createInsertQuery(testEmployeeList.get(0)));
	}

	// Insert Specified Reimbursement Query Test
	// Should, for a given reimbursement, create a query that will insert that reimbursement into the table
	@Test public void insertSpecifiedReimbursementQueryTest() throws IllegalArgumentException, IllegalAccessException {
		String testQueryString = "INSERT INTO tableName "
								+ "(reimbursementSource, requestedBy, resolvedBy) "
								+ "VALUES ('Test Source 2', 3, 2);";
		assertEquals(testQueryString, QueryProcessor.createInsertQuery(testReimbursementList.get(1)));
	}
	
	// Insert Null Reimbursement Request Query Test
	// Should, for a given reimbursement, properly create an insertion query when no requestor or resolver is specified
	@Test public void insertNullReimbursementRequestQueryTest() throws IllegalArgumentException, IllegalAccessException {
		String testQueryString = "INSERT INTO tableName (reimbursementSource) VALUES ('Test Source 3');";
		assertEquals(testQueryString, QueryProcessor.createInsertQuery(testReimbursementList.get(2)));
	}
	
	@Test
	public void insertReimbursementNoSourceQueryTest() throws IllegalArgumentException, IllegalAccessException{
		String testQueryString = "INSERT INTO tableName (reimbursementSource, requestedBy) VALUES ('', 1);";
		assertEquals(testQueryString, QueryProcessor.createInsertQuery(testReimbursementList.get(3)));
	}
	
}
