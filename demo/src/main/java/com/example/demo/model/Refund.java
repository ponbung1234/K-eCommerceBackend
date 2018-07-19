package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "refund")
public class Refund {
	@Id
	@Column(name = "refund_id")
	private int id;
	@Column(name = "ecustomer_id")
	private int ecustomer_id;
	@Column(name = "order_id")
	private int order_id;
	@Column(name = "ref_product_id")
	private int ref_product_id;

	@Column(name = "reason")
	private String reason;
	@Column(name = "refundStatus")
	private String refundStatus;

	public Refund() {

	}

	public Refund(int ecustomer_id, int order_id, int ref_product_id, String reason, String refundStatus) {
		super();
		this.ecustomer_id = ecustomer_id;
		this.order_id = order_id;
		this.ref_product_id = ref_product_id;
		this.reason = reason;
		this.refundStatus = refundStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEcustomer_id() {
		return ecustomer_id;
	}

	public void setEcustomer_id(int ecustomer_id) {
		this.ecustomer_id = ecustomer_id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getRef_product_id() {
		return ref_product_id;
	}

	public void setRef_product_id(int ref_product_id) {
		this.ref_product_id = ref_product_id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}

}
