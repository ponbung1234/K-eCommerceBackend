package com.example.demo.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.OrderDetail;
import com.example.demo.model.RefundDetail;
import com.example.demo.model.Users;
import com.example.service.CustomUserDetailsService;
import com.example.service.OrderDetailService;
import com.example.service.RefundDetailService;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

@RestController
@RequestMapping("/refund")
@CrossOrigin(allowedHeaders = "*")
public class RefundController {
	@Autowired
	private RefundDetailService refundService;
	@Autowired
	private CustomUserDetailsService userService;
	@Autowired
	private OrderDetailService ordDetailService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getRefund() throws SQLException, IOException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Users user = userService.loadUserByUsername(auth.getPrincipal().toString());

		JsonFactory jsonFactory = new JsonFactory();
		ByteArrayOutputStream json_temp = new ByteArrayOutputStream();
		JsonGenerator jsonGenerator = jsonFactory.createGenerator(json_temp, JsonEncoding.UTF8);
		Map<Integer, RefundDetail> refDetails = refundService.findAllByuserId(user.getId());
		List<OrderDetail> orderDetail = ordDetailService.findProductIdByecusId(user.getId());
		System.out.println("order detail : " + orderDetail.size());
		Iterator<OrderDetail> itr = orderDetail.iterator();
		jsonGenerator.writeRaw('[');
		int index = 0;
		int size = refDetails.size();
		System.out.println("refund " + size);
		while (index < size) {

			RefundDetail refDetail = refDetails.get(index);
			refDetail.setOrderDetail(itr.next());
			index++;
			jsonGenerator.writeStartObject();
			jsonGenerator.writeNumberField("item_id", refDetail.getOrderDetail().getItemId());
			jsonGenerator.writeNumberField("order_id", refDetail.getOrderDetail().getOrder_id());
			jsonGenerator.writeStringField("product_name", refDetail.getProduct_name());
			jsonGenerator.writeNumberField("price", refDetail.getPrice());
			jsonGenerator.writeStringField("pro_image_path", refDetail.getPro_image_path());
			jsonGenerator.writeStringField("product_description", refDetail.getProduct_description());
			jsonGenerator.writeStringField("date", refDetail.getOrderDetail().getOrder().getDate());
			jsonGenerator.writeNumberField("product_id", refDetail.getId());

			jsonGenerator.writeEndObject();
			if (index < size) {
				jsonGenerator.writeRaw(',');
				jsonGenerator.writeRaw('\n');
			}
		}
		jsonGenerator.writeRaw(']');
		jsonGenerator.close();

		return json_temp.toString();

	}

}
