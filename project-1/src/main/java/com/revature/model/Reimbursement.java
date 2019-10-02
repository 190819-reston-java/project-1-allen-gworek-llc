package com.revature.model;

public class Reimbursement implements Comparable<Reimbursement>, AppUsable {

	private int id;
	private String reimbursementSource;
	private int requestedBy;
	private int resolvedBy;
	private String dollarAmount;
	private boolean isApproved;
	private String imageURL;
	private String requestedByName;
	private String resolvedByName;
	
	public Reimbursement() {
		this.id = -1;
		this.reimbursementSource = "";
		this.requestedBy = -1;
		this.resolvedBy = -1;
		this.isApproved = false;
		this.imageURL = "";
		this.dollarAmount = "0";
		this.requestedByName = "";
		this.resolvedByName = "";
	}
	
	public Reimbursement(int id, String reimbursementSource, int requestedBy, int resolvedBy, boolean isApproved,
			String imageURL, String dollarAmount) {
		this.id = id;
		this.reimbursementSource = reimbursementSource;
		this.requestedBy = requestedBy;
		this.resolvedBy = resolvedBy;
		this.isApproved = isApproved;
		this.imageURL = imageURL;
		this.dollarAmount = dollarAmount;
		this.requestedByName = "";
		this.resolvedByName = "";
	}
	
	public Reimbursement(String reimbursementSource, String imageURL) {
		this(-1, reimbursementSource, -1, -1, false, imageURL, "0");
	}
	
	public Reimbursement(String reimbursementSource) {
		this(reimbursementSource, "");
	}
	
	public Reimbursement(int requestedBy) {
		this(-1, "", requestedBy, -1, false, "", "0");
	}
	
	public Reimbursement(int ID, String reimbursementSource, int requestedBy, int resolvedBy, boolean isApproved) {
		this(ID, reimbursementSource, requestedBy, resolvedBy, isApproved, "", "0");
	}
	
	public Reimbursement(int ID, String reimbursementSource, int requestedBy, int resolvedBy) {
		this(ID, reimbursementSource, requestedBy, resolvedBy, false, "", "0");
	}
	
	public Reimbursement(String reimbursementSource, int requestedBy, int resolvedBy) {
		this(-1, reimbursementSource, requestedBy, resolvedBy, false, "", "0");
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReimbursementSource() {
		return reimbursementSource;
	}

	public void setReimbursementSource(String reimbursementSource) {
		this.reimbursementSource = reimbursementSource;
	}

	public int getRequestedBy() {
		return requestedBy;
	}

	public void setRequestedBy(int requestedBy) {
		this.requestedBy = requestedBy;
	}

	public int getResolvedBy() {
		return resolvedBy;
	}

	public void setResolvedBy(int resolvedBy) {
		this.resolvedBy = resolvedBy;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
	
	public String getDollarAmount() {
		return dollarAmount;
	}
	
	public void setDollarAmount(String dollarAmount) {
		this.dollarAmount = dollarAmount;
	}

	public String getRequestedByName() {
		return requestedByName;
	}

	public void setRequestedByName(String requestedByName) {
		this.requestedByName = requestedByName;
	}

	public String getResolvedByName() {
		return resolvedByName;
	}

	public void setResolvedByName(String resolvedByName) {
		this.resolvedByName = resolvedByName;
	}

	public boolean getApprovedOrDenied() {
		if(isApproved & resolvedBy != -1) {
			return true;
		}
		else {
			return false;
		}
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
