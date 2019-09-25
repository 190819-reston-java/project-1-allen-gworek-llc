package com.revature.model;

import com.revature.annotations.Getter;
import com.revature.annotations.Setter;

public class Reimbursement implements Comparable<Reimbursement>, AppUsable {

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
	
	@Getter
	public int getId() {
		return id;
	}
	
	@Setter
	public void setId(int id) {
		this.id = id;
	}
	
	@Getter
	public String getReimbursementSource() {
		return reimbursementSource;
	}
	
	@Setter
	public void setReimbursementSource(String reimbursementSource) {
		this.reimbursementSource = reimbursementSource;
	}
	
	@Getter
	public int getRequestedBy() {
		return requestedBy;
	}
	
	@Setter
	public void setRequestedBy(int requestedBy) {
		this.requestedBy = requestedBy;
	}
	
	@Getter
	public int getResolvedBy() {
		return resolvedBy;
	}
	
	@Setter
	public void setResolvedBy(int resolvedBy) {
		this.resolvedBy = resolvedBy;
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
