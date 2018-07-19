package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CartRepository;
import com.example.demo.model.Cart;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRep;
	
	public List<Cart> getCartBycustId(int id){
		return cartRep.findByecustId(id);
	}
	public void saveCart(Cart cart){
		cartRep.save(cart);
	}
//	public void updateCart(Cart cart) {
//		cartRep.update(cart);
//	}
}
