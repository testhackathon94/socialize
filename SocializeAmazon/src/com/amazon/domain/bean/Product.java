package com.amazon.domain.bean;

import java.math.BigDecimal;
import java.util.Set;

public class Product {

	private Integer id;
	private String name;
	private String image;
	private BigDecimal price;
	private BigDecimal rating;
	private Set<FriendsFeed> friendsFeedSet;
	private Set<GoodBuy> goodBuySet;

	
	
	public Set<FriendsFeed> getFriendsFeedSet() {
		return friendsFeedSet;
	}
	public void setFriendsFeedSet(Set<FriendsFeed> friendsFeedSet) {
		this.friendsFeedSet = friendsFeedSet;
	}
	public Set<GoodBuy> getGoodBuySet() {
		return goodBuySet;
	}
	public void setGoodBuySet(Set<GoodBuy> goodBuySet) {
		this.goodBuySet = goodBuySet;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getRating() {
		return rating;
	}
	public void setRating(BigDecimal rating) {
		this.rating = rating;
	}
	
	
	
}
