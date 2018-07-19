package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer>{
	public OrderDetail findByorderId(Integer ids);


}
