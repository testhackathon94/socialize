package com.amazon.domain.bean.vo;

import java.math.BigDecimal;

import javax.persistence.Column;

import com.amazon.domain.bean.Product;
import com.amazon.domain.bean.User;

public class ProductVO {

	private Integer id;
	private String name;
	private String image;
	private BigDecimal price;
	private BigDecimal rating;
	
	public ProductVO() {
		// TODO Auto-generated constructor stub
	}
	
	public ProductVO(Product product) {
		populate(product);
	}

	public void populate(Product product){
		this.id = product.getId();
		this.name = product.getName();
		this.price = product.getPrice();
		this.image = product.getImage();
		this.rating = product.getRating();
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
