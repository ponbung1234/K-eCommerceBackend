package com.example.demo.controllers;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Cart;
import com.example.service.CartService;

@RestController
@RequestMapping("/addCart")
@CrossOrigin(allowedHeaders="*")
public class AddCartController {
	@Autowired
	private CartService cartService;
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String cart(@RequestBody String cart) throws SQLException, IOException {
	
		//Product_id,amount
		String[] temp = cart.split(",");
//		int product_id = Integer.parseInt(temp[0]);
//		int amount = Integer.parseInt(temp[1]);
		
		Cart cartItem = new Cart(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]));
//		if(cartService.getCartBycustId(1).equals(null)) {
			cartService.saveCart(cartItem);
//		}else {
//			cartService.updateCart(cartItem);
//		}


		return "Action sucessful";
	}
}
