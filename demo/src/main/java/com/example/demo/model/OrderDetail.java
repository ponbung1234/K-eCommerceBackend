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
@Table(name = "order_detail")
public class OrderDetail {
	@Column(name = "order_id")
	private int orderId;
	@Column(name = "product_id")
	private int product_id;
	@Id
	@Column(name = "item_id")
	private int item_id;
	@Column(name = "status")
	private String status;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "order_orderDetail", joinColumns = @JoinColumn(name = "item_id", referencedColumnName = "item_id"), inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "order_id"))
	private Order order;

	
	public OrderDetail(int orderId, int product_id, String status, Order order, int item_id) {
		super();
		this.orderId = orderId;
		this.product_id = product_id;
		this.status = status;
		this.order = order;
		this.item_id = item_id;
		
	}
	public OrderDetail() {
		
	}
	public int getOrder_id() {
		return orderId;
	}
	public void setOrder_id(int order_id) {
		this.orderId = order_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getItemId() {
		return item_id;
	}
	public void setItemId(int item_id) {
		this.item_id = item_id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	@Override
	public String toString() {
		return "OrderDetail [orderId=" + orderId + ", product_id=" + product_id + ", item_id=" + item_id + ", status="
				+ status + ", order=" + order + "]";
	}
	
}
