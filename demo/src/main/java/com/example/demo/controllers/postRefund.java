package com.example.demo.controllers;

import java.io.IOException;
import java.sql.SQLException;

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

import com.example.demo.model.Refund;
import com.example.demo.model.Users;
import com.example.service.CustomUserDetailsService;
import com.example.service.RefundService;

@RestController
@RequestMapping("/postRefund")
@CrossOrigin(allowedHeaders = "*")
public class postRefund {
	@Autowired
	private RefundService refService;
	@Autowired
	private CustomUserDetailsService userService;

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String refund(@RequestBody String refund) throws SQLException, IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Users user = userService.loadUserByUsername(auth.getPrincipal().toString());

		String[] temp = refund.split(",");
		Refund refundItem = new Refund(user.getId(), Integer.parseInt(temp[3]), Integer.parseInt(temp[4]), temp[2], "requested");
		refService.saveRefund(refundItem);
//		int item_id = Integer.parseInt(temp[0]);
//		int amount = Integer.parseInt(temp[1]);
//		String reasonn = temp[2];
//		int order_id = Integer.parseInt(temp[3]);
//		int product_id = Integer.parseInt(temp[4]);
		return "Action sucessful";

	}
}
