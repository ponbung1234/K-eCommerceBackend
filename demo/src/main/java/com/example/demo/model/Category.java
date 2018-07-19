package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_categories")
public class Category {
	@Id
	@Column(name = "category_id")
	private int categoryID;
	@Column(name = "category_name")
	private String category_name;
	
	public Category() {

	}

	public Category(Category category) {
		super();
		this.categoryID = category.getCategory_id();
		this.category_name = category.getCategory_name();

	}
	
	public int getCategory_id() {
		return categoryID;
	}
	public void setCategory_id(int category_id) {
		this.categoryID = category_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	

}
