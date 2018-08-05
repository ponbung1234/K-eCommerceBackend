package com.example.demo.controllers;

import java.io.IOException;
import java.sql.SQLException;
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
import com.example.demo.model.Order;
import com.example.demo.model.Payment;
import com.example.demo.model.Users;
import com.example.service.CartService;
import com.example.service.CheckoutService;
import com.example.service.CustomUserDetailsService;

@RestController
@RequestMapping("/checkout")
@CrossOrigin(allowedHeaders = "*")
public class CheckoutController {
	@Autowired
	private CheckoutService checkSer;

	@Autowired
	private CartService cartSer;

	@Autowired
	private CustomUserDetailsService userService;

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	// total price,payment type,ecus id
	public @ResponseBody String checkout(@RequestBody String checkout) throws SQLException, IOException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Users user = userService.loadUserByUsername(auth.getPrincipal().toString());
		String[] temp = checkout.split(",");
		double totalPrice = Double.parseDouble(temp[0]);

		int ecusID = user.getId();

		List<Cart> carts = checkSer.selectAllCart(ecusID);
		checkSer.insertOrder(new Order(ecusID, totalPrice));
		Order order = checkSer.findByecusIdAndPrice(ecusID, totalPrice);
		int order_id = order.getOrder_id();
		System.out.println("order_id = " + order_id);
		Payment payment = new Payment(order_id, "Unpaid", temp[1]);
		checkSer.insertPayment(payment);
		Iterator<Cart> itr = carts.iterator();
		order.setOrder_id(order_id);

		while (itr.hasNext()) {
			Cart cart = itr.next();
			for (int i = 0; i < cart.getCart_amount(); i++) {
				Integer	 lastItemId = checkSer.getLastItemId();
				System.out.println("lastItemId = "+lastItemId);
				if(checkSer.getLastItemId() == null)
					lastItemId = 0;
				else
					lastItemId = checkSer.getLastItemId();
				
				checkSer.insertOrderDetail(order_id, lastItemId + 1, cart.getProduct_id(), "Confirmed");
			}

			cartSer.deleteCart(cart.getId(), ecusID, cart.getProduct_id());
		}

		return null;

	}
}
