package com.revature.service;

import java.lang.reflect.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.exception.InvalidArgumentFormatException;
import com.revature.exception.UnmatchableTypesException;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;

public abstract class QueryProcessor {

	public static <T> String createInsertQuery(T t) throws IllegalArgumentException, IllegalAccessException {
		ArrayList<ArrayList<Object>> fieldsAndValues = getFieldsAndValues(t);

		String insertQuery = "INSERT INTO tableName (columnNames) VALUES (insertionValues);";

		StringBuilder columnNames = new StringBuilder();
		StringBuilder insertionValues = new StringBuilder();

		for (ArrayList<Object> fieldAndValue : fieldsAndValues) {
			String tableName = fieldAndValue.get(0).toString();
			String tableValue = fieldAndValue.get(1).toString();

			if (tableName == "id") {
				continue;
			}

			if (tableValue.equals("-1")) {
				if (tableName == "resolvedBy" || tableName == "requestedBy") {
					continue;
				}
			}

			columnNames.append(tableName);
			columnNames.append(", ");

			if (fieldAndValue.get(1).getClass().getTypeName() != "java.lang.String") {
				insertionValues.append(tableValue);
			} else {
				insertionValues.append("'");
				insertionValues.append(delimitThisString(tableValue));
				insertionValues.append("'");
			}

			insertionValues.append(", ");
		}

		insertionValues.delete(insertionValues.length() - 2, insertionValues.length());
		columnNames.delete(columnNames.length() - 2, columnNames.length());

		insertQuery = insertQuery.replaceAll("insertionValues", insertionValues.toString());
		System.out.println("Value of insertionValues before replacing: " + insertionValues);
		System.out.println("Query before replacing columnNames: " + insertQuery);
		return insertQuery.replaceFirst("columnNames", columnNames.toString());
	}

	public static String specifyTable(String queryToSpecify, String tableSelection) {
		return queryToSpecify.replaceAll("tableName", tableSelection);
	}

	public static <T> String specifyUpdateConditions(String queryToSpecify, T... tList)
			throws InvalidArgumentFormatException {
		StringBuilder updateConditions = new StringBuilder();

		boolean isTableName = true;
		for (T t : tList) {
			if (isTableName & t.getClass().getCanonicalName() != "java.lang.String") {
				throw new InvalidArgumentFormatException();
			}

			if (isTableName) {
				updateConditions.append(t + " = ");
			} else {
				if (t.getClass().getCanonicalName() != "java.lang.String") {
					updateConditions.append(t.toString());
				} else {
					updateConditions.append("'");
					updateConditions.append(delimitThisString((String)t));
					updateConditions.append("'");
				}

				updateConditions.append(" AND ");
			}

			isTableName = !isTableName;
		}

		if (!isTableName) {
			throw new InvalidArgumentFormatException("Did not pass in an even number of arguments!");
		}

		updateConditions.delete(updateConditions.length() - 5, updateConditions.length());

		return queryToSpecify.replaceAll("updateConditions", updateConditions.toString());
	}

	public static <T> String createSelectQuery(T... tList) throws InvalidArgumentFormatException {
		String selectQuery = "SELECT * FROM tableName WHERE searchConditions";

		StringBuilder searchConditions = new StringBuilder();

		boolean isTableName = true;
		for (T t : tList) {
			if (isTableName & t.getClass().getCanonicalName() != "java.lang.String") {
				throw new InvalidArgumentFormatException();
			}

			if (isTableName) {
				searchConditions.append(t + " = ");
			} else {
				if (t.getClass().getCanonicalName() != "java.lang.String") {
					searchConditions.append(t.toString());
				} else {
					searchConditions.append("'");
					searchConditions.append(delimitThisString((String)t));
					searchConditions.append("'");
				}

				searchConditions.append(" AND ");
			}

			isTableName = !isTableName;
		}

		if (!isTableName) {
			throw new InvalidArgumentFormatException("Did not pass in an even number of arguments!");
		}

		searchConditions.delete(searchConditions.length() - 5, searchConditions.length());
		searchConditions.append(";");

		return selectQuery.replaceAll("searchConditions", searchConditions.toString());
	}

	public static <T> String createSelectQuery(T t) throws IllegalArgumentException, IllegalAccessException {
		ArrayList<ArrayList<Object>> fieldsAndValues = getFieldsAndValues(t);

		String selectQuery = "SELECT * FROM tableName WHERE searchConditions";

		StringBuilder searchConditions = new StringBuilder();

		for (ArrayList<Object> fieldAndValue : fieldsAndValues) {
			String tableName = fieldAndValue.get(0).toString();
			String tableValue = fieldAndValue.get(1).toString();

			searchConditions.append(tableName);
			searchConditions.append(" = ");

			if (fieldAndValue.get(1).getClass().getTypeName() != "java.lang.String") {
				searchConditions.append(tableValue);
			} else {
				searchConditions.append("'");
				searchConditions.append(delimitThisString(tableValue));
				searchConditions.append("'");
			}

			searchConditions.append(", ");
		}

		searchConditions.delete(searchConditions.length() - 2, searchConditions.length());
		searchConditions.append(";");

		return selectQuery.replaceAll("searchConditions", searchConditions.toString());
	}

	public static <T> String createUpdateQuery(T... tlist) throws InvalidArgumentFormatException {

		String updateQuery = "UPDATE tableName " + "SET updateValues " + "WHERE updateConditions;";

		StringBuilder updateValues = new StringBuilder();

		boolean isTableName = true;
		for (T t : tlist) {
			if (isTableName & t.getClass().getCanonicalName() != "java.lang.String") {
				throw new InvalidArgumentFormatException();
			}

			if (isTableName) {
				updateValues.append(t + " = ");
			} else {
				if (t.getClass().getCanonicalName() != "java.lang.String") {
					updateValues.append(t.toString());
				} else {
					updateValues.append("'");
					updateValues.append(delimitThisString((String)t));
					updateValues.append("'");
				}

				updateValues.append(", ");
			}

			isTableName = !isTableName;
		}

		if (!isTableName) {
			throw new InvalidArgumentFormatException("Did not pass in an even number of arguments!");
		}

		updateValues.delete(updateValues.length() - 2, updateValues.length());

		return updateQuery.replaceFirst("updateValues", updateValues.toString());
	}

	public static <T> String createUpdateQuery(T t) throws IllegalArgumentException, IllegalAccessException {

		ArrayList<ArrayList<Object>> fieldsAndValues = getFieldsAndValues(t);

		String updateQuery = "UPDATE tableName " + "SET updateValues " + "WHERE updateConditions;";

		StringBuilder updateValues = new StringBuilder();

		for (ArrayList<Object> fieldAndValue : fieldsAndValues) {
			String tableName = fieldAndValue.get(0).toString();
			String tableValue = fieldAndValue.get(1).toString();

			if (tableName == "id") {
				continue;
			}

			if (tableValue.equals("-1")) {
				if (tableName == "resolvedBy" || tableName == "requestedBy") {
					continue;
				}
			}

			updateValues.append(tableName);
			updateValues.append(" = ");

			if (fieldAndValue.get(1).getClass().getTypeName() != "java.lang.String") {
				updateValues.append(tableValue);
			} else {
				updateValues.append("'");
				updateValues.append(delimitThisString(tableValue));
				updateValues.append("'");
			}

			updateValues.append(", ");
		}

		updateValues.delete(updateValues.length() - 2, updateValues.length());

		return updateQuery.replaceAll("updateValues", updateValues.toString());
	}

	public static <T> String createUpdateToMatchOther(T first, T other)
			throws UnmatchableTypesException, IllegalArgumentException, IllegalAccessException {

		if (first.getClass().getTypeName() != other.getClass().getTypeName()) {
			throw new UnmatchableTypesException();
		}

		ArrayList<ArrayList<Object>> firstTypesAndFields = new ArrayList<ArrayList<Object>>();
		ArrayList<ArrayList<Object>> otherTypesAndFields = new ArrayList<ArrayList<Object>>();

		firstTypesAndFields = getFieldsAndValues(first);
		otherTypesAndFields = getFieldsAndValues(other);

		ArrayList<Object> formattedUpdateConditionsArray = new ArrayList<Object>();
		ArrayList<Object> formattedUpdateValuesArray = new ArrayList<Object>();

		for (int index = 0; index < firstTypesAndFields.size(); index++) {
			
			if (firstTypesAndFields.get(index).get(0).equals("id")) {
				continue;
			}
			
			formattedUpdateConditionsArray.add(firstTypesAndFields.get(index).get(0));
			formattedUpdateConditionsArray.add(firstTypesAndFields.get(index).get(1));

			formattedUpdateValuesArray.add(firstTypesAndFields.get(index).get(0));
			formattedUpdateValuesArray.add(otherTypesAndFields.get(index).get(1));
		}

		Object[] fieldsToBePassedForConditions = formattedUpdateConditionsArray.toArray();
		Object[] fieldsToBePassedForValues = formattedUpdateValuesArray.toArray();

		String updateQuery = createUpdateQuery(fieldsToBePassedForValues);
		return specifyUpdateConditions(updateQuery, fieldsToBePassedForConditions);
	}

	private static <T> ArrayList<ArrayList<Object>> getFieldsAndValues(T t)
			throws IllegalArgumentException, IllegalAccessException {
		/**
		 * Returns an array of (initially) two-item arrays of type T that contain a
		 * String for the field name and the value contained within the field for the
		 * target object
		 */
		ArrayList<ArrayList<Object>> returnArray = new ArrayList<ArrayList<Object>>();

		for (Field field : t.getClass().getDeclaredFields()) {
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

	public static Employee createEmployeeFromQueryResults(ResultSet employeeResults)
			throws IllegalArgumentException, IllegalAccessException, SQLException {

		Employee createdEmployee = new Employee();

		System.out.println(createdEmployee);
		for (Field currentField : createdEmployee.getClass().getDeclaredFields()) {
			currentField.setAccessible(true);
			if (currentField.getType().toString().equals("class java.lang.String")) {
				if (employeeResults.getString(currentField.getName()) == null) {
					currentField.set(createdEmployee, "");
				}
				currentField.set(createdEmployee, employeeResults.getString(currentField.getName()));
				System.out.println("Created Employee value for " + currentField.getName());
			}
			if (currentField.getType().toString().equals("int")) {

				if (employeeResults.getInt(currentField.getName()) == 0) {
					currentField.set(createdEmployee, -1);
				} else {
					currentField.set(createdEmployee, employeeResults.getInt(currentField.getName()));
					System.out.println("Created Employee value for " + currentField.getName());
				}
			}
			if (currentField.getType().toString().equals("boolean")) {
				currentField.set(createdEmployee, employeeResults.getBoolean(currentField.getName()));
				System.out.println("Created Employee value for " + currentField.getName());
			}
		}

		return createdEmployee;
	}

	public static Reimbursement createReimbursementFromQueryResults(ResultSet reimbursementResults)
			throws IllegalArgumentException, IllegalAccessException, SQLException {
		Reimbursement createdReimbursement = new Reimbursement();

		for (Field currentField : createdReimbursement.getClass().getDeclaredFields()) {
			currentField.setAccessible(true);
			if (currentField.getType().toString().equals("class java.lang.String")) {
				if (reimbursementResults.getString(currentField.getName()) == null) {
					currentField.set(createdReimbursement, "");
				} else {
					currentField.set(createdReimbursement, reimbursementResults.getString(currentField.getName()));
				}
			}
			if (currentField.getType().toString().equals("int")) {
				if (reimbursementResults.getInt(currentField.getName()) == 0) {
					currentField.set(createdReimbursement, -1);
				} else {
					currentField.set(createdReimbursement, reimbursementResults.getInt(currentField.getName()));
				}
			}
			if (currentField.getType().toString().equals("boolean")) {
				currentField.set(createdReimbursement, reimbursementResults.getBoolean(currentField.getName()));
			}
		}

		return createdReimbursement;
	}
	
	private static String delimitThisString(String stringToDelimit) {
		return stringToDelimit.replaceAll("'", "''");
	
	}
}
