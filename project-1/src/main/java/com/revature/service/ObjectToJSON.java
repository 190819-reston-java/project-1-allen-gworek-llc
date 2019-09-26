package com.revature.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.revature.model.AppUsable;
import java.util.Date;

public class ObjectToJSON {

	public static <T> String convertObjectToJSON(T objectToConvert) throws JsonProcessingException {
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		
		String postJson = mapper.writeValueAsString(objectToConvert);
		
		return postJson;
		
	}
}
