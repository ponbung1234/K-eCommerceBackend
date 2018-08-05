package com.example.demo.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Database;
import com.example.demo.model.Users;
import com.example.service.CustomUserDetailsService;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

@RestController
@RequestMapping("/cart")
@CrossOrigin(allowedHeaders = "*")
public class CartController {
	@Autowired
	private CustomUserDetailsService userService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	// @RequestParam(value = "ecustomer_id", required = true) String ecus_id
	public @ResponseBody String getCart() throws IOException, SQLException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Users user = userService.loadUserByUsername(auth.getPrincipal().toString());
		JsonFactory jsonFactory = new JsonFactory();
		ByteArrayOutputStream json_temp = new ByteArrayOutputStream();
		JsonGenerator jsonGenerator = jsonFactory.createGenerator(json_temp, JsonEncoding.UTF8);
		Database db = new Database();
		Connection con = db.connect();
		Statement stm = con.createStatement();
		ResultSet s = stm.executeQuery(
				"select * from cart natural join products where cart.product_id = products.product_id AND ecustomer_id = "
						+ user.getId() + ";");
		if (!s.next())
			return "Nothing in the cart for this user";
		jsonGenerator.writeRaw('[');
		s.beforeFirst();
		while (s.next()) {
			jsonGenerator.writeStartObject();
			jsonGenerator.writeNumberField("product_id", s.getInt(1));
			jsonGenerator.writeNumberField("cart_id", s.getInt(2));
			jsonGenerator.writeNumberField("ecustomer_id", s.getInt(3));

			jsonGenerator.writeNumberField("cart_amount", s.getInt(4));
			jsonGenerator.writeNumberField("category_id", s.getInt(5));
			jsonGenerator.writeStringField("product_name", s.getString(6));

			jsonGenerator.writeNumberField("price", s.getDouble(7));
			jsonGenerator.writeNumberField("product_amount", s.getInt(8));

			jsonGenerator.writeStringField("pro_image_path", s.getString(9));
			jsonGenerator.writeStringField("product_description", s.getString(10));
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
