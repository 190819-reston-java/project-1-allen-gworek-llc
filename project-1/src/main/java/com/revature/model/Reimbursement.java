package com.revature.model;

public class Reimbursement implements Comparable<Reimbursement>, AppUsable {

	private int id;
	private String reimbursementSource;
	private int requestedBy;
	private int resolvedBy;
	private boolean isApproved;
	private String imageURL;
	
	
	
	public Reimbursement(int id, String reimbursementSource, int requestedBy, int resolvedBy, boolean isApproved,
			String imageURL) {
		this.id = id;
		this.reimbursementSource = reimbursementSource;
		this.requestedBy = requestedBy;
		this.resolvedBy = resolvedBy;
		this.isApproved = isApproved;
		this.imageURL = imageURL;
	}
	
	public Reimbursement(String reimbursementSource, String imageURL) {
		this(-1, reimbursementSource, -1, -1, false, imageURL);
	}
	
	public Reimbursement(String reimbursementSource) {
		this(reimbursementSource, "");
	}
	
	public Reimbursement(int requestedBy) {
		this(-1, "", requestedBy, -1, false, "");
	}
	
	public Reimbursement(int ID, String reimbursementSource, int requestedBy, int resolvedBy, boolean isApproved) {
		this(ID, reimbursementSource, requestedBy, resolvedBy, isApproved, "");
	}
	
	public Reimbursement(int ID, String reimbursementSource, int requestedBy, int resolvedBy) {
		this(ID, reimbursementSource, requestedBy, resolvedBy, false, "");
	}
	
	public Reimbursement(String reimbursementSource, int requestedBy, int resolvedBy) {
		this(-1, reimbursementSource, requestedBy, resolvedBy, false, "");
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
