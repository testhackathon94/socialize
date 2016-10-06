package com.amazon.netty.bean;

public class CustomerBean {
	private String limit;
	private String skip;
	private String search;
	private String segment;
	private Object filter;
	private String serviceID;
	
	public void setFilter(Object filter) {
		this.filter = filter;
	}
	public Object getFilter() {
		return filter;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	public String getSkip() {
		return skip;
	}
	public void setSkip(String skip) {
		this.skip = skip;
	}
	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getSegment() {
		return segment;
	}
	public void setSegment(String segment) {
		this.segment = segment;
	}
	public String getServiceID() {
		return serviceID;
	}
	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}
	
	
}
