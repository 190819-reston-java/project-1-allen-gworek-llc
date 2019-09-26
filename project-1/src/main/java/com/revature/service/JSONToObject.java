package com.revature.service;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public class JSONToObject {

	public static Employee convertEmployeeJSONToObject(String JSONValues) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		Employee returnEmployee = mapper.readValue(JSONValues, Employee.class);
		
		return returnEmployee;
	}
	
	public static Reimbursement convertReimbursementJSONtoObject(String JSONValues) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		
		Reimbursement returnReimbursement = mapper.readValue(JSONValues, Reimbursement.class);
		
		return returnReimbursement;
	}
	
	
}
