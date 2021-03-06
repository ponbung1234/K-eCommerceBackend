package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Order;

public interface OrderRepository extends JpaRepository<Order,Integer>{
	public List<Order> findByecusId(int id);
	@SuppressWarnings("unchecked")
	public Order save(Order order);
	
	public Order findByecusIdAndPrice(int ecusId,double price);
}
