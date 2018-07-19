package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Products {

	@Id
	@Column(name = "product_id")
	private int id;
	@Column(name = "category_id")
	private int category_id;
	@Column(name = "product_name")
	private String product_name;
	@Column(name = "price")
	private double price;
	@Column(name = "product_amount")
	private int product_amount;
	@Column(name = "pro_image_path")
	private String pro_image_path;
	@Column(name = "product_description")
	private String product_description;

	public Products() {

	}

	public Products(Products product) {
		super();
		this.id = product.getProduct_id();
		this.category_id = product.getCategory_id();
		this.product_name = product.getProduct_name();
		this.price = product.getPrice();
		this.product_amount = product.getProduct_amount();
		this.pro_image_path = product.getPro_image_path();
		this.product_description = product.getProduct_description();
	}

	public int getProduct_id() {
		return id;
	}

	public void setProduct_id(int product_id) {
		this.id = product_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getProduct_amount() {
		return product_amount;
	}

	public void setProduct_amount(int product_amount) {
		this.product_amount = product_amount;
	}

	public String getPro_image_path() {
		return pro_image_path;
	}

	public void setPro_image_path(String pro_image_path) {
		this.pro_image_path = pro_image_path;
	}

	public String getProduct_description() {
		return product_description;
	}

	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}

}
