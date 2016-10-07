package com.amazon.domain.bean.vo;

import java.util.Date;

import com.amazon.domain.bean.FriendsFeed;

public class FriendFeedVO {

	private UserVO userVo;
	private ProductVO productVo;
	private String action;
	private String content;
	private Date eventDate;
	private GoodBuyVO goodBuyVO;
	
	public FriendFeedVO() {
		// TODO Auto-generated constructor stub
	}
	
	public FriendFeedVO(FriendsFeed ff, UserVO uservo, ProductVO productVo) {
		this.userVo = uservo;
		this.productVo = productVo;
		this.action = ff.getAction();
		this.content = ff.getContent();
		this.eventDate = ff.getEventTime();
	}

	
	
	public GoodBuyVO getGoodBuyVO() {
		return goodBuyVO;
	}

	public void setGoodBuyVO(GoodBuyVO goodBuyVO) {
		this.goodBuyVO = goodBuyVO;
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
