package com.example.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.OrderDetailRepository;
import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;

@Service
public class OrderDetailService {
	@Autowired
	private OrderDetailRepository orderRep;

	public List<OrderDetail> findOrderDetailByOrderIds(List<Order> orders) {
		List<Integer> ids = new ArrayList<Integer>();
		List<OrderDetail> result = new ArrayList<OrderDetail>();
		Iterator<Order> itr = orders.iterator();
		while (itr.hasNext()) {
			result.add(orderRep.findByorderId(new Integer(itr.next().getOrder_id())));
		}
		return  result;
	}
}
