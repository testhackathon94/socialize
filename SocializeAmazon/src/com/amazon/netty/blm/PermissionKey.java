package com.amazon.netty.blm;



public enum PermissionKey {
	Dashboard("DSHB"),
	Appointments("APPT"),
	Customers("CUST"),
	Campaigns("CAMG"),
	Reports("REP"),
	Settings("SET"),
	Feedback("FEED"),
	Notification("NOTI");
	
	
	
	String key;
	
	private PermissionKey (String key){
		this.key = key;
	}
	
	public String getKey(){
		return key;
	}
	
	
	
}
