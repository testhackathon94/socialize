package com.amazon.domain.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Good_Buy_Response")
public class GoodBuyResponse {

	@Id 
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	@Column(name="good_buy_id")
	private GoodBuy goodBuy;
	
	@Column(name = "friends_id")
	private User friend;
	
	@Column(name = "response")
	private String response;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "timestamp")
	private Date eventTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public GoodBuy getGoodBuy() {
		return goodBuy;
	}
	public void setGoodBuy(GoodBuy goodBuy) {
		this.goodBuy = goodBuy;
	}
	public User getFriend() {
		return friend;
	}
	public void setFriend(User friend) {
		this.friend = friend;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getEventTime() {
		return eventTime;
	}
	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}
	
	
}
