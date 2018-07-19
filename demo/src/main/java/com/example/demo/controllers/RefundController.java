package com.example.demo.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.RefundDetail;
import com.example.service.RefundDetailService;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

@RestController
@RequestMapping("/refund")
@CrossOrigin(allowedHeaders="*")
public class RefundController {
	@Autowired
	private RefundDetailService refundService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getRefund()
			throws SQLException, IOException {
		Integer a = new Integer(1);
		Iterator<RefundDetail> itr = refundService.findAllByuserId(a).iterator();
		JsonFactory jsonFactory = new JsonFactory();
		ByteArrayOutputStream json_temp = new ByteArrayOutputStream();
		JsonGenerator jsonGenerator = jsonFactory.createGenerator(json_temp, JsonEncoding.UTF8);
	
		jsonGenerator.writeRaw('[');
		while (itr.hasNext()) {
			RefundDetail refDetail = itr.next();
			jsonGenerator.writeStartObject();
			jsonGenerator.writeNumberField("item_id", refDetail.getOrderDetail().getOrder_id());
			jsonGenerator.writeNumberField("order_id", refDetail.getOrderDetail().getItemId());
			jsonGenerator.writeStringField("product_name", refDetail.getProduct_name());
			jsonGenerator.writeNumberField("price", refDetail.getPrice());
			jsonGenerator.writeStringField("pro_image_path", refDetail.getPro_image_path());
			jsonGenerator.writeStringField("product_description", refDetail.getProduct_description());
			jsonGenerator.writeStringField("date", refDetail.getOrderDetail().getOrder().getDate());
			jsonGenerator.writeNumberField("product_id", refDetail.getId());

			jsonGenerator.writeEndObject();
			if (!itr.hasNext()) {
				jsonGenerator.writeRaw(',');
				jsonGenerator.writeRaw('\n');
			}
		}
		jsonGenerator.writeRaw(']');
		jsonGenerator.close();
		

		return json_temp.toString();

	}
	
}
