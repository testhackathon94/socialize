package com.amazon.domain.bean;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

public class User {

	@Id 
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "user_name")
	private String name;
	
	@Column(name = "facebook_user_id")
	private String facebook_id;
	
	@Column(name = "user_image")
	private String image;
	
	@Column(name = "user_city")
	private String city;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Friend> friendSet;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<FriendsFeed> friedsFeedSet;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Order> orderSet;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<MyActivity> myActivitySet;
	
	
	
	public Set<Friend> getFriendSet() {
		return friendSet;
	}
	public void setFriendSet(Set<Friend> friendSet) {
		this.friendSet = friendSet;
	}
	public Set<FriendsFeed> getFriedsFeedSet() {
		return friedsFeedSet;
	}
	public void setFriedsFeedSet(Set<FriendsFeed> friedsFeedSet) {
		this.friedsFeedSet = friedsFeedSet;
	}
	public Set<Order> getOrderSet() {
		return orderSet;
	}
	public void setOrderSet(Set<Order> orderSet) {
		this.orderSet = orderSet;
	}
	public Set<MyActivity> getMyActivitySet() {
		return myActivitySet;
	}
	public void setMyActivitySet(Set<MyActivity> myActivitySet) {
		this.myActivitySet = myActivitySet;
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
	public String getFacebook_id() {
		return facebook_id;
	}
	public void setFacebook_id(String facebook_id) {
		this.facebook_id = facebook_id;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
	
}
