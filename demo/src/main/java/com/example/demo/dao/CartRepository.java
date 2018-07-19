package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Cart;

public interface CartRepository extends JpaRepository<Cart,Integer>{
	public List<Cart> findByecustId(int id);
	@SuppressWarnings("unchecked")
	public Cart save(Cart cart);
}
