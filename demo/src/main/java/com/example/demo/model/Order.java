package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@Column(name = "order_id")
	private int order_id;
	@Column(name = "ecustomer_id")
	private int ecusId;
	@Column(name = "total_price")
	private double price;
	@Column(name = "date")
	private String date;
	
	
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getEcus_id() {
		return ecusId;
	}
	public void setEcus_id(int ecus_id) {
		this.ecusId = ecus_id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
