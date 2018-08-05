package com.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.OrderDetailRepository;
import com.example.demo.dao.OrderRepository;
import com.example.demo.dao.RefundDetailRepository;
import com.example.demo.model.Order;
import com.example.demo.model.OrderDetail;
import com.example.demo.model.RefundDetail;

@Service
public class RefundDetailService {
	@Autowired
	private RefundDetailRepository refRep;
	@Autowired
	private OrderDetailRepository ordDetailRep;
	@Autowired
	private OrderRepository ordRep;

	public Map<Integer, RefundDetail> findAllByuserId(int id) {
		List<Order> orders = ordRep.findByecusId(id);
		Iterator<Order> itr = orders.iterator();
		List<OrderDetail> result = new ArrayList<>();
		while (itr.hasNext()) {
			List<OrderDetail> tempResult = ordDetailRep.findAllByorderIds(itr.next().getOrder_id());
			Iterator<OrderDetail> temp = tempResult.iterator();
			while (temp.hasNext()) {
				result.add(temp.next());
			}
		}

		List<Integer> product_ids = new ArrayList<Integer>();
		Iterator<OrderDetail> itr2 = result.iterator();

		while (itr2.hasNext()) {
			OrderDetail ordDetail = itr2.next();
			Integer temp = new Integer(ordDetail.getProduct_id());

			product_ids.add(temp);
		}
		Iterator<OrderDetail> itr4 = result.iterator();
		Map<Integer, RefundDetail> finalResult = new HashMap<>();
		Iterator<Integer> itr3 = product_ids.iterator();
		int index = 0;
		while (itr3.hasNext()) {
			int temp = itr3.next();

			RefundDetail refDetail = refRep.findAllByid(temp);
			OrderDetail orderDetail = itr4.next();

			refDetail.setOrderDetail(orderDetail);
			finalResult.put(index, refDetail);
			index++;

		}

		int size = finalResult.size();
		int i = 0;
		while (i < size) {
			i++;
		}
		return finalResult;

	}
}
