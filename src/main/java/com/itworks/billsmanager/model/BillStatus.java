package com.itworks.billsmanager.model;

public enum BillStatus {
	
	PENDING("Pending"),
	PAID("Paid");
	
	private String description;
	
	BillStatus(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
}
