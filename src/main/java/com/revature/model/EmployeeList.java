package com.revature.model;

import java.util.ArrayList;

public class EmployeeList extends ArrayList<Employee>{

	public EmployeeList(Employee...employees) {

		for(Employee newEmployee : employees) {
			this.add(newEmployee);
		}
	}
	
}
