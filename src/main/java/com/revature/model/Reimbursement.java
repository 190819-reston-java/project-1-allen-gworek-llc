package com.revature.model;

public class Reimbursement implements Comparable<Reimbursement>{

	private int id;
	private String reimbursementSource;
	private int requestedBy;
	private int resolvedBy;
	
	public Reimbursement(int ID, String reimbursementSource, int requestor, int resolver) {
		this.id = ID;
		this.reimbursementSource = reimbursementSource;
		this.requestedBy = requestor;
		this.resolvedBy = resolver;
	}
	public Reimbursement(String reimbursementSource, int requestor, int resolver) {
		this(-1, reimbursementSource, requestor, resolver);
	}
	
	public Reimbursement(String reimbursementSource) {
		this(-1, reimbursementSource, -1, -1);
	}
	
	public Reimbursement(String reimbursementSource, int requestor) {
		this(-1, reimbursementSource, requestor, -1);
	}
	
	public Reimbursement(int requestor) {
		this(-1, "", requestor, -1);
	}
	
	public int getID() {
		return this.id;
	}
	public void setID(int ID) {
		this.id = ID;
	}
	public String getReimbursementSource() {
		return reimbursementSource;
	}
	public void setReimbursementSource(String reimbursementSource) {
		this.reimbursementSource = reimbursementSource;
	}
	public int getRequestor() {
		return requestedBy;
	}
	public void setRequestor(int requestor) {
		this.requestedBy = requestor;
	}
	public int getResolver() {
		return resolvedBy;
	}
	public void setResolver(int resolver) {
		this.resolvedBy = resolver;
	}
	
	public int compareTo(Reimbursement other) {
		if(this.id == other.id) {
			return 0;
		}
		if(this.id > other.id) {
			return 1;
		}
		else {
			return -1;
		}
	}
}
