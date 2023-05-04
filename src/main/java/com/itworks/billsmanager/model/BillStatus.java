package com.itworks.billsmanager.model;

public enum BillStatus {
	
	PENDING("Pending"),
	RECEIVED("Received");
	
	private String description;
	
	BillStatus(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	
}
