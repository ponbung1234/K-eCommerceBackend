package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {
	@Id
	@Column(name = "cart_id")
	private int id;
	@Column(name = "ecustomer_id")
	private int ecustId;
	@Column(name = "product_id")
	private int product_id;
	@Column(name = "cart_amount")
	private int cart_amount;
	
	public Cart(int product_id, int cart_amount) {
		super();
		this.product_id = product_id;
		this.cart_amount = cart_amount;
	}
	public Cart(int ecustId,int product_id, int cart_amount) {
		super();
		this.ecustId = ecustId;
		this.product_id = product_id;
		this.cart_amount = cart_amount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEcustId() {
		return ecustId;
	}
	public void setEcustId(int ecustId) {
		this.ecustId = ecustId;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getCart_amount() {
		return cart_amount;
	}
	public void setCart_amount(int cart_amount) {
		this.cart_amount = cart_amount;
	}
	
	
}
