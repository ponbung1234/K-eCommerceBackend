package com.example.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CartRepository;
import com.example.demo.model.Cart;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRep;
	
	public List<Cart> getAllCartBycustId(int id ){
		return cartRep.findAllByecustId(id);
	}
	public List<Cart> findAllCart(){
		return cartRep.findAll();
	}
	public void saveCart(Cart cart){
		cartRep.save(cart);
	}
	public void saveCart(int a,int b,int c,int d) {
		cartRep.save(a,b,c,d);
	}
	public void deleteCart(int cart_id,int ecust_id,int product_id) {
		cartRep.delete(cart_id,ecust_id,product_id);
	}
	public void deleteAllCart(int ecust_id) {
		cartRep.deleteAll(ecust_id);
	}
	public List<Integer> findAllId(){
		List<Cart> carts = cartRep.findAll();
		List<Integer> result = new ArrayList<>();
		Iterator<Cart> itr = carts.iterator();
		while(itr.hasNext()) {
			result.add(itr.next().getId());
		}
		return result;
	}
}
