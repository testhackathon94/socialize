package com.amazon.netty.bean;

import java.math.BigDecimal;

public class ProductBean {

	private Integer customerId;
	private Integer productId;
	private String rate;
	private String review;
	private Boolean isRecommended;
	private Boolean isWished;
	private Boolean isPurchased;
	private Integer goodBuyId;
	private String goodBuyResponse;
	private String comment;
	
	
	
	public String getGoodBuyResponse() {
		return goodBuyResponse;
	}

	public void setGoodBuyResponse(String goodBuyResponse) {
		this.goodBuyResponse = goodBuyResponse;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getGoodBuyId() {
		return goodBuyId;
	}

	public void setGoodBuyId(Integer goodBuyId) {
		this.goodBuyId = goodBuyId;
	}

	public Boolean getIsWished() {
		return isWished;
	}

	public void setIsWished(Boolean isWished) {
		this.isWished = isWished;
	}

	public Boolean getIsPurchased() {
		return isPurchased;
	}

	public void setIsPurchased(Boolean isPurchased) {
		this.isPurchased = isPurchased;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Boolean getIsRecommended() {
		return isRecommended;
	}

	public void setIsRecommended(Boolean isRecommended) {
		this.isRecommended = isRecommended;
	}

	public Integer getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
}
