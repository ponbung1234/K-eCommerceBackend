package com.example.demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getCart(@RequestParam(value = "ecustomer_id", required = true) String ecus_id) throws IOException, SQLException {
		JsonFactory jsonFactory = new JsonFactory();
		ByteArrayOutputStream json_temp = new ByteArrayOutputStream();
		JsonGenerator jsonGenerator = jsonFactory.createGenerator(json_temp, JsonEncoding.UTF8);
		Database db = new Database();
		Connection con = db.connect();
		Statement stm = con.createStatement();
		ResultSet s = stm.executeQuery("SELECT * FROM Cart WHERE ecustomer_id = "+Integer.parseInt(ecus_id));
		if(!s.next())
			return "Nothing in the cart for this user";
		jsonGenerator.writeRaw('[');
		s.beforeFirst();
		while (s.next()) {
			jsonGenerator.writeStartObject();
			jsonGenerator.writeNumberField("cart_id", s.getInt(1));
			jsonGenerator.writeNumberField("ecustomer_id", s.getInt(2));
			jsonGenerator.writeNumberField("product_id", s.getInt(3));
			jsonGenerator.writeNumberField("cart_amount", s.getInt(4));
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
