package com.amazon.domain.bean;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "Good_Buy")
public class GoodBuy {
 
	@Id 
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="product_id")	
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="user_id")	
	private User user;
	
	@Column(name = "timestamp")
	private Date eventTime;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "goodBuy")
	private Set<GoodBuyResponse> goodBuyResponseSet;
	
	public Set<GoodBuyResponse> getGoodBuyResponseSet() {
		return goodBuyResponseSet;
	}
	public void setGoodBuyResponseSet(Set<GoodBuyResponse> goodBuyResponseSet) {
		this.goodBuyResponseSet = goodBuyResponseSet;
	}
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
	public Date getEventTime() {
		return eventTime;
	}
	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}
	
	
	
}
