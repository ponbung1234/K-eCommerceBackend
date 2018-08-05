package com.example.demo.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Payment;
import com.example.demo.model.Users;
import com.example.service.CustomUserDetailsService;
import com.example.service.PaymentService;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

@RestController
@RequestMapping("/order")
@CrossOrigin(allowedHeaders="*")
public class OrderController {
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private CustomUserDetailsService userService;
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getOrder()
			throws SQLException, IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Users user = userService.loadUserByUsername(auth.getPrincipal().toString());

		Iterator<Payment> itr = paymentService.findOrderById(user.getId()).iterator();
		JsonFactory jsonFactory = new JsonFactory();
		ByteArrayOutputStream json_temp = new ByteArrayOutputStream();
		JsonGenerator jsonGenerator = jsonFactory.createGenerator(json_temp, JsonEncoding.UTF8);
		
		jsonGenerator.writeRaw('[');

		while (itr.hasNext()) {
			Payment order = itr.next();
			jsonGenerator.writeStartObject();

			jsonGenerator.writeNumberField("order_id", order.getOrderId());
			jsonGenerator.writeNumberField("ecustomer_id", order.getOrder().getEcus_id());
			jsonGenerator.writeNumberField("total_price", order.getOrder().getPrice());
			jsonGenerator.writeStringField("orderDate", order.getDate());
			jsonGenerator.writeStringField("status", order.getStatus());
			jsonGenerator.writeStringField("paymentType_name", order.getPaymentType_name());

			jsonGenerator.writeEndObject();
			if (itr.hasNext()) {
				jsonGenerator.writeRaw(',');
				jsonGenerator.writeRaw('\n');
			}
		}
		jsonGenerator.writeRaw(']');
		jsonGenerator.close();
		

		return json_temp.toString();
	}
}
