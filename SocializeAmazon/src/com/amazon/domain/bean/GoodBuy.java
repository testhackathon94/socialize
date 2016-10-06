package com.amazon.domain.bean;

import java.util.Date;
import java.util.Set;

public class GoodBuy {
 
	private Integer id;
	private Product product;
	private User user;
	private Date eventTime;
	private Set<GoodBuyResponse> goodBuyResponseSet;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getEventTime() {
		return eventTime;
	}
	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}
	
	
	
}
