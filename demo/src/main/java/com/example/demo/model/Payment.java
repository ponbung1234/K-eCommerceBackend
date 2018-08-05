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
	private int paymentId;
	@Column(name = "order_id")
	private int orderId;

	@Column(name = "pay_image_path")
	private String pay_image_path;
	@Column(name = "status")
	private String status;
	@Column(name = "date")
	private String date;
	@Column(name = "payment_type_name")
	private String paymentType_name;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "order_payment", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "order_id", insertable = false, updatable = false))
	private Order order;

	public Payment() {

	}

	public Payment(int orderId, String status, String paymentType_name) {
		super();
		this.orderId = orderId;
		this.status = status;
		this.paymentType_name = paymentType_name;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int payment_id) {
		this.paymentId = payment_id;
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

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", orderId=" + orderId + ", pay_image_path=" + pay_image_path
				+ ", status=" + status + ", date=" + date + ", paymentType_name=" + paymentType_name + ", order="
				+ order + "]";
	}

}
