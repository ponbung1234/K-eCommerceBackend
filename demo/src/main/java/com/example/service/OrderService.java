package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.OrderRepository;
import com.example.demo.model.Order;

@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRep;

	
	public List<Order> findOrderById(int id){
		return orderRep.findByecusId(id);
	}
	public List<Order> findOrderByIds(List<String> id){
		return null;
	}
//	public List<Payment> findOrderPaymentById(int id){
//		List<Order> orders = orderRep.findByecusId(id);
//		Iterator<Order> itr = orders.iterator();
//		List<Integer> order_ids = new ArrayList<Integer>();
//		while (itr.hasNext()) {
//			Integer temp = new Integer(itr.next().getOrder_id());
//			System.out.println("Order id :"+temp);
//			//System.out.println(paymentRep.findByorderID(temp));
//			order_ids.add(temp);
//		}
//		
//		List<Payment> payment = new ArrayList<>();
//		Iterator<Integer> itr2 = order_ids.iterator();
//		while (itr2.hasNext()) {
//			Payment order = paymentRep.findByorderID(itr2.next());
//			payment.add(order);
//		}
//		return payment;
//		
//	}
	
}
