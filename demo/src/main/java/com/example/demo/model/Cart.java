package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart implements Serializable{

	 @Column(name = "cart_id")
	 private int id;
	 
	 @Column(name = "ecustomer_id")
	 private int ecustId;
	 @Id
	 @Column(name = "product_id")
	 private int product_id;

	@Column(name = "cart_amount")
	private int cart_amount;

	

	public Cart(int product_id, int cart_amount) {
		super();
		this.product_id = product_id;
		this.cart_amount = cart_amount;
	}

	public Cart(int ecustId, int product_id, int cart_amount) {
		super();
		this.ecustId = ecustId;
		this.product_id = product_id;
		this.cart_amount = cart_amount;
	}

	public Cart() {
		// TODO Auto-generated constructor stub
	}

	public Cart(int id, int ecus_id, int product_id, int amount) {
		this.id = id;
		this.ecustId = ecus_id;
		this.product_id = product_id;
		this.cart_amount = amount;
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

	public Cart incrementAmount(Cart cart) {
		cart.setCart_amount(++cart.cart_amount);
		return cart;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", ecustId=" + ecustId + ", product_id=" + product_id + ", cart_amount="
				+ cart_amount + "]";
	}

}
