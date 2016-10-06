package com.amazon.domain.bean.vo;

import com.amazon.domain.bean.User;

public class UserVO {

	private Integer id;
	private String name;
	private String facebookId;
	private String image;
	private String city;
	
	public UserVO() {
		// TODO Auto-generated constructor stub
	}
	
	public UserVO(User user) {
		populate(user);
	}

	public void populate(User user){
		this.id = user.getId();
		this.name = user.getName();
		this.facebookId = user.getFacebook_id();
		this.image = user.getImage();
		this.city = user.getCity();
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

	public String getFacebookId() {
		return facebookId;
	}

	public void setFacebookId(String facebookId) {
		this.facebookId = facebookId;
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
