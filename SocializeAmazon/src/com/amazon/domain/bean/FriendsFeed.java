package com.amazon.domain.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Friends_Feed")
public class FriendsFeed {
 
	@Id 
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="user_id")	
	private User user;
	
	@ManyToOne
	@JoinColumn(name="product_id")	
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="friend_id")	
	private User friend;
	
	@Column(name = "action")
	private String action;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "timestamp")
	private Date eventTime;
	
	@ManyToOne
	@JoinColumn(name="good_buy_id")
	private GoodBuy goodbuy;
	public GoodBuy getGoodbuy() {
		return goodbuy;
	}
	public void setGoodbuy(GoodBuy goodbuy) {
		this.goodbuy = goodbuy;
	}
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
	public User getFriend() {
		return friend;
	}
	public void setFriend(User friend) {
		this.friend = friend;
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
	public Date getEventTime() {
		return eventTime;
	}
	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

	
	
}
