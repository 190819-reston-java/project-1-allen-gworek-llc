package com.revature.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ModelTests {

	static Employee newEmployee;
	static Reimbursement newReimbursement;
	static EmployeeList newEmployeeList;
	static ReimbursementList newReimbursementList;
	
	@Before
	public void setUp() throws Exception {
		newEmployee = new Employee(1, "Full Name", "Job Title", "Address", "Email", "Password", false);
		newReimbursement = new Reimbursement(1, "Reimbursement Source", 1, 2);
		newEmployeeList = new EmployeeList(newEmployee, newEmployee, newEmployee, newEmployee);
		newReimbursementList = new ReimbursementList(newReimbursement, newReimbursement, newReimbursement, newReimbursement);
	}

	@After
	public void tearDown() throws Exception {
		newEmployee = null;
		newReimbursement = null;
		newEmployeeList = null;
	}

	// Employee Object Test
	// Should accurately return an Employee object
	@Test
	public void employeeObjectTest() {
		
		assertEquals(1, newEmployee.getID());
		assertEquals("Full Name", newEmployee.getFullName());
		assertEquals("Job Title", newEmployee.getJobTitle());
		assertEquals("Address", newEmployee.getAddress());
		assertEquals("Email", newEmployee.getEmail());
		assertEquals("Password", newEmployee.getPassword());
		assertEquals(false, newEmployee.isManager());
	}
	
	// Reimbursement Object Test
	// Should accurately return a Reimbursement object
	
	@Test
	public void reimbursementObjectTest() {
		
		assertEquals(1, newReimbursement.getID());
		assertEquals("Reimbursement Source", newReimbursement.getReimbursementSource());
		assertEquals(1, newReimbursement.getRequestor());
		assertEquals(2, newReimbursement.getResolver());
	}

	// EmployeeList Object Test
	// Should accurately return an EmployeeList object
	
	@Test
	public void employeeListObjectTest() {
		
		ArrayList<Employee> comparisonList = new ArrayList<Employee>();
		for(int i = 0; i < 4; i++) {
			comparisonList.add(newEmployee);
		}
		
		for(int i = 0; i < 4; i++) {
			assertEquals(comparisonList.get(i), newEmployeeList.get(i));
		}
	}
	
	// ReimbursementList Object Test
	// Should accurately return a ReimbursementList object
	
	@Test
	public void reimbursementListObjectTest() {
		ArrayList<Reimbursement> comparisonList = new ArrayList<Reimbursement>();
		for(int i = 0; i < 4; i++) {
			comparisonList.add(newReimbursement);
		}
		
		for(int i = 0; i < 4; i++) {
			assertEquals(comparisonList.get(i), newReimbursementList.get(i));
		}
	}
	
}
