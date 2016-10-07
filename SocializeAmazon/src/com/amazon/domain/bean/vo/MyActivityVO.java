package com.amazon.domain.bean.vo;

import java.util.Date;

import com.amazon.domain.bean.FriendsFeed;
import com.amazon.domain.bean.MyActivity;

public class MyActivityVO {

	private UserVO userVo;
	private ProductVO productVo;
	private String action;
	private String content;
	private Date eventDate;
	
	public MyActivityVO() {
		// TODO Auto-generated constructor stub
	}
	
	public MyActivityVO(MyActivity myActivity, UserVO uservo, ProductVO productVo) {
		this.userVo = uservo;
		this.productVo = productVo;
		this.action = myActivity.getAction();
		this.content = myActivity.getContent();
		this.eventDate = myActivity.getEventtime();
	}

	public UserVO getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVO userVo) {
		this.userVo = userVo;
	}

	public ProductVO getProductVo() {
		return productVo;
	}

	public void setProductVo(ProductVO productVo) {
		this.productVo = productVo;
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

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	
}
