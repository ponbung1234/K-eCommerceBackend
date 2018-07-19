package com.example.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.OrderDetailRepository;
import com.example.demo.dao.OrderRepository;
import com.example.demo.dao.RefundRepository;
import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;
import com.example.demo.model.RefundDetail;

@Service
public class RefundService {
	@Autowired
	private RefundRepository refRep;
	@Autowired
	private OrderDetailRepository ordDetailRep;
	@Autowired
	private OrderRepository ordRep;

	public List<RefundDetail> findAllByuserId(int id) {
		List<Order> orders = ordRep.findByecusId(id);
		Iterator<Order> itr = orders.iterator();
		List<OrderDetail> result = new ArrayList<OrderDetail>();
		while (itr.hasNext()) {
			Integer temp = new Integer(itr.next().getOrder_id());
			System.out.println("Order id :"+temp);
			System.out.println(ordDetailRep.findByorderId(temp));
			result.add(ordDetailRep.findByorderId(temp));

		}

		List<Integer> product_ids = new ArrayList<Integer>();
		Iterator<OrderDetail> itr3 = result.iterator();
		while (itr3.hasNext()) {
			Integer temp =new Integer(itr3.next().getProduct_id());
			System.out.println("Product id : " + temp);
			product_ids.add(temp);
		}
		return refRep.findAllByid(product_ids);

	}
}
