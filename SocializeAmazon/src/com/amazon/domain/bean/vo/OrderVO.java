package com.amazon.domain.bean.vo;

import java.util.Date;

import com.amazon.domain.bean.Order;

public class OrderVO {

	private ProductVO productVo;
	private UserVO userVo;
	private Date eventTime;
	
	public OrderVO(Order order) {
		this.eventTime = order.getOrderDate();
		this.productVo = new ProductVO(order.getProduct());
		this.userVo = new UserVO(order.getUser());
	}

	public ProductVO getProductVo() {
		return productVo;
	}

	public void setProductVo(ProductVO productVo) {
		this.productVo = productVo;
	}

	public UserVO getUserVo() {
		return userVo;
	}

	public void setUserVo(UserVO userVo) {
		this.userVo = userVo;
	}

	public Date getEventTime() {
		return eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}
	
	
	
}
