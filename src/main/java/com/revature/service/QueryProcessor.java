package com.revature.service;

import java.lang.reflect.*;
import java.util.ArrayList;

import com.revature.exception.InvalidArgumentFormatException;

public abstract class QueryProcessor {
	
	public static <T> String createInsertQuery(T t) throws IllegalArgumentException, IllegalAccessException {
		ArrayList<ArrayList<Object>> fieldsAndValues = getFieldsAndValues(t);
		
		String insertQuery = "INSERT INTO tableName (columnNames) VALUES (insertionValues);";
		
		StringBuilder columnNames = new StringBuilder();
		StringBuilder insertionValues = new StringBuilder();
		
		for(ArrayList<Object> fieldAndValue : fieldsAndValues) {
			String tableName = fieldAndValue.get(0).toString();
			String tableValue = fieldAndValue.get(1).toString();
			
			columnNames.append(tableName);
			columnNames.append(", ");
			
			if (fieldAndValue.get(1).getClass().getTypeName() != "java.lang.String") {
				insertionValues.append(tableValue);
			}
			else {
				insertionValues.append("'");
				insertionValues.append(tableValue);
				insertionValues.append("'");
			}
			
			insertionValues.append(", ");
		}
		
		insertionValues.delete(insertionValues.length() - 2, insertionValues.length());
		columnNames.delete(columnNames.length() - 2, columnNames.length());
		
		insertQuery = insertQuery.replaceAll("insertionValues", insertionValues.toString());
		return insertQuery.replaceFirst("columnNames", columnNames.toString());
	}
	
	
	public static <T> String createSelectQuery(T...tList) throws InvalidArgumentFormatException {
		String selectQuery = "SELECT * FROM tableName WHERE searchConditions";
		
		StringBuilder searchConditions = new StringBuilder();
		
		boolean isTableName = true;
		for(T t : tList) {
				if(isTableName & t.getClass().getCanonicalName() != "java.lang.String") {
					throw new InvalidArgumentFormatException();
				}
				
				if(isTableName) {
					searchConditions.append(t + " = ");
				}
				else {
					if(t.getClass().getCanonicalName() != "java.lang.String") {
						searchConditions.append(t.toString());
					}
					else {
						searchConditions.append("'");
						searchConditions.append(t);
						searchConditions.append("'");
					}
					
					searchConditions.append(", ");
				}
			
			isTableName = !isTableName;
		}
		
		if(!isTableName) {
			throw new InvalidArgumentFormatException("Did not pass in an even number of arguments!");
		}
		
		searchConditions.delete(searchConditions.length() - 2, searchConditions.length());
		searchConditions.append(";");
		
		return selectQuery.replaceAll("searchConditions", searchConditions.toString());
	}
	
	public static <T> String createSelectQuery(T t) throws IllegalArgumentException, IllegalAccessException {
		ArrayList<ArrayList<Object>> fieldsAndValues = getFieldsAndValues(t);
		
		String selectQuery = "SELECT * FROM tableName WHERE searchConditions";
		
		StringBuilder searchConditions = new StringBuilder();
		
		for(ArrayList<Object> fieldAndValue : fieldsAndValues) {
			String tableName = fieldAndValue.get(0).toString();
			String tableValue = fieldAndValue.get(1).toString();
			
			searchConditions.append(tableName);
			searchConditions.append(" = ");
			
			if (fieldAndValue.get(1).getClass().getTypeName() != "java.lang.String") {
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
			field.setAccessible(true);
			String columnName = field.getName();
			Object columnField = field.get(t);
			
			fieldAndValue.add(columnName);
			fieldAndValue.add(columnField);
			
			returnArray.add(fieldAndValue);
		}
		
		return returnArray;
	}
}
