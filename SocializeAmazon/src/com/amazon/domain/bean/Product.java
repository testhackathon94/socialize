package com.amazon.domain.bean;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "Product")
public class Product {

	@Id 
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "product_name")
	private String name;
	
	@Column(name = "product_image")
	private String image;
	
	@Column(name = "product_price")
	private BigDecimal price;
	
	@Column(name = "product_rating")
	private BigDecimal rating;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")	
	private Set<FriendsFeed> friendsFeedSet;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")	
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
