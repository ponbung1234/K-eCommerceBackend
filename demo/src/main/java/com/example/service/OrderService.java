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
}
