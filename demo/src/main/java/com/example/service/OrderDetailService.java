package com.example.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.OrderDetailRepository;
import com.example.demo.dao.OrderRepository;
import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;

@Service
public class OrderDetailService {
	@Autowired
	private OrderDetailRepository orderRep;
	@Autowired 
	private OrderRepository ordRep;

	public List<OrderDetail> findOrderDetailByOrderIds(List<Order> orders) {
		List<OrderDetail> result = new ArrayList<OrderDetail>();
		Iterator<Order> itr = orders.iterator();
		while (itr.hasNext()) {
			result.add(orderRep.findByorderId(new Integer(itr.next().getOrder_id())));
		}
		return  result;
	}
	public List<OrderDetail> findProductIdByecusId(int id){
		List<Order> orders = ordRep.findByecusId(id);
		Iterator<Order> itr = orders.iterator();
		List<OrderDetail> result  = new ArrayList<OrderDetail>();
		while(itr.hasNext()) {
			List<OrderDetail> banana = orderRep.findAllByorderIds(itr.next().getOrder_id());
			Iterator<OrderDetail> grape = banana.iterator();
			while(grape.hasNext()) {
				result.add(grape.next());
			}
		}
		return result;
		

	}
}
