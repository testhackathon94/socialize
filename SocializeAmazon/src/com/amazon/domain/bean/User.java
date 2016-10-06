package com.amazon.domain.bean;

import java.util.Set;

public class User {

	private Integer id;
	private String name;
	private String facebook_id;
	private String image;
	private String city;
	private Set<Friend> friendSet;
	private Set<FriendsFeed> friedsFeedSet;
	private Set<Order> orderSet;
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
