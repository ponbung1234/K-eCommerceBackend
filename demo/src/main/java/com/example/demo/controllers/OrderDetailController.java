package com.example.demo.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Database;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

@RestController
@RequestMapping("/orderDetail")
@CrossOrigin(allowedHeaders="*")
public class OrderDetailController {
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getOrder(@RequestParam(value = "ecustomer_id", required = false) String ecus_id) throws IOException, SQLException {
		JsonFactory jsonFactory = new JsonFactory();
		ByteArrayOutputStream json_temp = new ByteArrayOutputStream();
		JsonGenerator jsonGenerator = jsonFactory.createGenerator(json_temp, JsonEncoding.UTF8);
		Database db = new Database();
		Connection con = db.connect();
		Statement stm = con.createStatement();
		ResultSet s = stm.executeQuery("select order_id,product_name,price,pro_image_path,product_description, COUNT(product_name) as amount,item_id from order_detail natural join orders,products where orders.ecustomer_id = 1 AND orders.order_id = order_detail.order_id\n" + 
				"			AND order_detail.product_id = products.product_id group by product_name, order_id;");
		if(!s.next())
			return "No order history";
		jsonGenerator.writeRaw('[');
		s.beforeFirst();
		while (s.next()) {
			jsonGenerator.writeStartObject();
			jsonGenerator.writeNumberField("order_id", s.getInt(1));
			jsonGenerator.writeStringField("product_name", s.getString(2));
			jsonGenerator.writeNumberField("price", s.getDouble(3));
			jsonGenerator.writeStringField("pro_image_path", s.getString(4));
			jsonGenerator.writeStringField("product_description", s.getString(5));
			jsonGenerator.writeNumberField("amount", s.getInt(6));
			jsonGenerator.writeNumberField("item_id", s.getInt(7));

			jsonGenerator.writeEndObject();
			if (!s.isLast()) {
				jsonGenerator.writeRaw(',');
				jsonGenerator.writeRaw('\n');
			}
		}
		jsonGenerator.writeRaw(']');
		jsonGenerator.close();
		db.close();
		return json_temp.toString();
	}
}
