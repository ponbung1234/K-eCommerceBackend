package com.example.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.OrderRepository;
import com.example.demo.dao.PaymentRepository;
import com.example.demo.model.Order;
import com.example.demo.model.Payment;

@Service
public class PaymentService {
	@Autowired
	private PaymentRepository paymentRep;
	@Autowired
	private OrderRepository orderRep;

	public List<Payment> findOrderById(int id) {
		List<Order> orders = orderRep.findByecusId(id);
		Iterator<Order> itr = orders.iterator();
		List<Payment> result = new ArrayList<>();
		while (itr.hasNext()) {
			Order order = itr.next();

			Payment payment = paymentRep.findByorderId(order.getOrder_id());
			payment.setOrder(order);

			result.add(payment);
		}

		return result;
	}

}
