package com.amazon.domain.bean.vo;

import java.util.Date;

import com.amazon.domain.bean.GoodBuyResponse;

public class GoodBuyResponseVO {

	private UserVO user;
	private String response;
	private String comment;
	private Date eventTime;
	
	public GoodBuyResponseVO(GoodBuyResponse goodBuyResponse) {
		this.response = goodBuyResponse.getResponse();
		this.comment = goodBuyResponse.getComment();
		this.eventTime = goodBuyResponse.getEventTime();
		this.user = new UserVO(goodBuyResponse.getFriend());
	}
	
	public UserVO getUser() {
		return user;
	}
	public void setUser(UserVO user) {
		this.user = user;
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
