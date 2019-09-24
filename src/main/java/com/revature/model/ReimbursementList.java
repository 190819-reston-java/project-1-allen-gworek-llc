package com.revature.model;

import java.util.ArrayList;

public class ReimbursementList extends ArrayList<Reimbursement>{

	public ReimbursementList(Reimbursement...reimbursements) {

		for(Reimbursement newReimbursement : reimbursements) {
			this.add(newReimbursement);
		}
	}

}
