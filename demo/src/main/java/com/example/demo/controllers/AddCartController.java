package com.example.demo.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Cart;
import com.example.demo.model.Users;
import com.example.service.CartService;
import com.example.service.CustomUserDetailsService;

@RestController
@RequestMapping("/addCart")
@CrossOrigin(allowedHeaders = "*")
public class AddCartController {
	@Autowired
	private CartService cartService;
	@Autowired
	private CustomUserDetailsService userService;

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String cart(@RequestBody String cart) throws SQLException, IOException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Users user = userService.loadUserByUsername(auth.getPrincipal().toString());

		
		// Product_id,amount
		String[] temp = cart.split(",");
		int product_id = Integer.parseInt(temp[0]);
		int amount = Integer.parseInt(temp[1]);
		
		if (!cartService.getAllCartBycustId(user.getId()).equals(new ArrayList<>())) {
			// update cart
			List<Cart> cartList = cartService.findAllCart();
			Iterator<Cart> itr = cartList.iterator();
			int flag = 0;
			int lastCartId=1;
			
			while (itr.hasNext()) {
				Cart cartTemp = itr.next();
				int cartId = cartTemp.getId();
				int prodId = cartTemp.getProduct_id();
				int curAmount = cartTemp.getCart_amount();
				if (cartTemp.getProduct_id() == product_id) {
					System.out.println("same product");
					cartService.deleteCart(cartId, user.getId(), prodId);
					cartService.saveCart(cartId, user.getId(), prodId, curAmount + amount);
					flag = 1;
				}
				if (!itr.hasNext()) {
					lastCartId = cartTemp.getId();
					System.out.println("last cart id = " + lastCartId);
				}
			}
			if (flag == 0) {
				// insert
				System.out.println("new product");
				cartService.saveCart(lastCartId,user.getId(),product_id,amount);
			}

		} else {
			System.out.println("new cart");
			List<Integer> newCartId = cartService.findAllId();
			int max = 1;
			Iterator<Integer> itr = newCartId.iterator();
			while (itr.hasNext()) {
				if (max < itr.next())
					max = itr.next();
			}
			System.out.println(max+1);
			cartService.saveCart(max+1, user.getId(), product_id, amount);
		}

		return "Action sucessful";
	}
}
