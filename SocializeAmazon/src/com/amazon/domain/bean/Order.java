package com.amazon.domain.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Order")
public class Order {

	@Id 
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "product_id")
	private Product product;
	
	@Column(name = "user_id")
	private User user;
	
	@Column(name = "timestamp")
	private Date orderDate;
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Boolean getIsRated() {
		return isRated;
	}
	public void setIsRated(Boolean isRated) {
		this.isRated = isRated;
	}
	public Boolean getIsReviewed() {
		return isReviewed;
	}
	public void setIsReviewed(Boolean isReviewed) {
		this.isReviewed = isReviewed;
	}
	public Boolean getIsRecommended() {
		return isRecommended;
	}
	public void setIsRecommended(Boolean isRecommended) {
		this.isRecommended = isRecommended;
	}
	private Boolean isRated;
	private Boolean isReviewed;
	private Boolean isRecommended;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
