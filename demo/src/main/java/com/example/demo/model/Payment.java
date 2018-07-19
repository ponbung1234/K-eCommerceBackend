package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment {
	@Id
	@Column(name = "payment_id")
	private int payment_id;
	@Column(name = "order_id")
	private int orderID;
	@Column(name = "pay_image_path")
	private String pay_image_path;
	@Column(name = "status")
	private String status;
	@Column(name = "date")
	private String date;
	@Column(name = "paymentType_name")
	private String paymentType_name;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "order_payment", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "order_id"))
	private Order order;
	
	public Order getOrder() {
		return order;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public int getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}
	public int getOrder_id() {
		return orderID;
	}
	public void setOrder_id(int order_id) {
		this.orderID = order_id;
	}
	public String getPay_image_path() {
		return pay_image_path;
	}
	public void setPay_image_path(String pay_image_path) {
		this.pay_image_path = pay_image_path;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPaymentType_name() {
		return paymentType_name;
	}
	public void setPaymentType_name(String paymentType_name) {
		this.paymentType_name = paymentType_name;
	}

	
}
