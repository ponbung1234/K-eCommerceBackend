package com.example.demo.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Database;
import com.example.demo.model.Order;
import com.example.demo.model.RefundDetail;
import com.example.service.OrderService;
import com.example.service.RefundService;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

@RestController
@RequestMapping("/order")
@CrossOrigin(allowedHeaders="*")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getOrder()
			throws SQLException, IOException {
		Integer a = new Integer(1);
		Iterator<Order> itr = orderService.findOrderPaymentById(a).iterator();
		JsonFactory jsonFactory = new JsonFactory();
		ByteArrayOutputStream json_temp = new ByteArrayOutputStream();
		JsonGenerator jsonGenerator = jsonFactory.createGenerator(json_temp, JsonEncoding.UTF8);
		
		
//		JsonFactory jsonFactory = new JsonFactory();
//		ByteArrayOutputStream json_temp = new ByteArrayOutputStream();
//		JsonGenerator jsonGenerator = jsonFactory.createGenerator(json_temp, JsonEncoding.UTF8);
//		Database db = new Database();
//		Connection con = db.connect();
//		Statement stm = con.createStatement();
//		ResultSet s = stm.executeQuery("select * from orders natural join payment,payment_type\n" + 
//				"where orders.order_id = payment.order_id AND payment.paymentType_id = payment_type.paymentType_id;");

		jsonGenerator.writeRaw('[');

		while (itr.hasNext()) {
			Order order = itr.next();
			jsonGenerator.writeStartObject();
			
//			jsonGenerator.writeNumberField("order_id", s.getInt(1));
//			jsonGenerator.writeNumberField("ecustomer_id", s.getInt(3));
//			jsonGenerator.writeNumberField("total_price", s.getDouble(4));
//			jsonGenerator.writeStringField("orderDate", s.getString(5));
//			jsonGenerator.writeStringField("status", s.getString(7));
//			jsonGenerator.writeStringField("paymentType_name", s.getString(10));
			jsonGenerator.writeNumberField("order_id", order.getOrder_id());
			jsonGenerator.writeNumberField("ecustomer_id", order.getEcusId());
			jsonGenerator.writeNumberField("total_price", order.getPrice());
			jsonGenerator.writeStringField("orderDate", order.getDate());
			jsonGenerator.writeStringField("status", order.getPayment().getStatus());
			jsonGenerator.writeStringField("paymentType_name", order.getPayment().getPaymentType_name());

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
