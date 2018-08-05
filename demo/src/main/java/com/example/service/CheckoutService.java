package com.example.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CartRepository;
import com.example.demo.dao.OrderDetailRepository;
import com.example.demo.dao.OrderRepository;
import com.example.demo.dao.PaymentRepository;
import com.example.demo.model.Cart;
import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;
import com.example.demo.model.Payment;

@Service
public class CheckoutService {
	@Autowired
	private CartRepository cartRep;
	@Autowired
	private OrderRepository orderRep;
	@Autowired
	private OrderDetailRepository orderDetailRep;
	@Autowired
	private PaymentRepository paymentRep;

	public List<Cart> selectAllCart(int ecus_id) {
		return cartRep.findAllByecustId(ecus_id);
	}

	public Order insertOrder(Order order) {
		return orderRep.save(order);
	}

	// public List<OrderDetail> insertOrderDetails(List<OrderDetail> orderDetail) {
	// return orderDetailRep.save(orderDetail);
	// }
	public void insertOrderDetail(int order_id, int item_id, int product_id, String status) {
		orderDetailRep.saveOrderDetail(order_id, item_id, product_id, status);
	}

	public Payment insertPayment(Payment payment) {
		return paymentRep.save(payment);
	}

	public Order findByecusIdAndPrice(int ecusId, double price) {
		return orderRep.findByecusIdAndPrice(ecusId, price);
	}

	public Integer getLastItemId() {
		return orderDetailRep.findLastitemId();

	}
}
