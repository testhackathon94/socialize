package com.amazon.netty.database;

public enum DatabaseConfig {
	Url("192.168.1.120"),
	Port("2480"),
	Dbname("mio"),
	UserName("root"),
	Password("gms123");
	
	String value;
	
	private DatabaseConfig(String value){
		this.value = value;
	}
	
	public String getValue(){
		return this.value;
	}
}
