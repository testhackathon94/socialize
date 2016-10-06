package com.amazon.domain.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "My_Activity")
public class MyActivity {

	@Id 
	@GeneratedValue
	@Column(name = "id")
	private Integer id;

	@Column(name = "user_id")
	private User user;

	@Column(name = "product_id")
	private Product product;
	
	@Column(name = "action")
	private String action;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "timestamp")
	private Date eventtime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getEventtime() {
		return eventtime;
	}
	public void setEventtime(Date eventtime) {
		this.eventtime = eventtime;
	}
	
	
	
}
