package com.revature.service;

import java.lang.reflect.*;
import java.util.ArrayList;

public class QueryProcessor {

	private static QueryProcessor queryProcessorSingleton = new QueryProcessor();
	
	private QueryProcessor() {}
	
	public QueryProcessor getQueryProcessor() {
		return queryProcessorSingleton;
	}
	
	public static <T> String createSelectQuery(T t) throws IllegalArgumentException, IllegalAccessException {
		ArrayList<ArrayList<Object>> fieldsAndValues = getFieldsAndValues(t);
		
		String selectQuery = "SELECT * FROM table WHERE searchConditions";
		
		StringBuilder searchConditions = null;
		
		for(ArrayList<Object> fieldAndValue : fieldsAndValues) {
			String tableName = fieldAndValue.get(0).toString();
			String tableValue = fieldAndValue.get(1).toString();
			
			searchConditions.append(tableName);
			searchConditions.append(" = ");
			
			if (fieldAndValue.get(1).getClass().getTypeName() != "String") {
				searchConditions.append(tableValue);
			}
			else {
				searchConditions.append("'");
				searchConditions.append(tableValue);
				searchConditions.append("'");
			}
			
			searchConditions.append(", ");
		}
		
		searchConditions.delete(searchConditions.length() - 2, searchConditions.length());
		searchConditions.append(";");
		
		return selectQuery.replaceAll("searchConditions", searchConditions.toString());
	}
	
	private static <T> ArrayList<ArrayList<Object>> getFieldsAndValues(T t) throws IllegalArgumentException, IllegalAccessException {
		/** 
		 * Returns an array of (initially) two-item arrays of type T that contain a String
		 * for the field name and the value contained within the field for the target object
		 * */
		ArrayList<ArrayList<Object>> returnArray = new ArrayList<ArrayList<Object>>();
		
		for(Field field : t.getClass().getDeclaredFields()) {
			ArrayList<Object> fieldAndValue = new ArrayList<Object>();
			
			String columnName = field.getName();
			Object columnField = field.get(t);
			
			fieldAndValue.add(columnName);
			fieldAndValue.add(columnField);
			
			returnArray.add(fieldAndValue);
		}
		
		return returnArray;
	}
}
