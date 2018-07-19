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

//select item_id,orders.order_id, product_name,price,pro_image_path,product_description, date,products.product_id from products,order_detail,orders \n" + 
//"where order_detail.product_id = products.product_id AND orders.order_id = order_detail.order_id AND orders.ecustomer_id =1;
@Entity
@Table(name = "products")
public class RefundDetail {
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
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "product_orderDetail", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	private OrderDetail orderDetail;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public OrderDetail getOrderDetail() {
		return orderDetail;
	}
	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}
	
	
}
