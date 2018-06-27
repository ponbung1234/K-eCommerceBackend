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
@RequestMapping("/order")
public class OrderController {
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getOrder(@RequestParam(value = "ecustomer_id", required = true) String ecus_id) throws IOException, SQLException {
		JsonFactory jsonFactory = new JsonFactory();
		ByteArrayOutputStream json_temp = new ByteArrayOutputStream();
		JsonGenerator jsonGenerator = jsonFactory.createGenerator(json_temp, JsonEncoding.UTF8);
		Database db = new Database();
		Connection con = db.connect();
		Statement stm = con.createStatement();
		ResultSet s = stm.executeQuery("SELECT * FROM Orders WHERE ecustomer_id = "+Integer.parseInt(ecus_id));
		if(!s.next())
			return "No order history";
		jsonGenerator.writeRaw('[');
		s.beforeFirst();
		while (s.next()) {
			jsonGenerator.writeStartObject();
			jsonGenerator.writeNumberField("order_id", s.getInt(1));
			jsonGenerator.writeNumberField("ecustomer_id", s.getInt(2));
			jsonGenerator.writeNumberField("total_price", s.getDouble(3));
			jsonGenerator.writeStringField("orderDate", s.getString(4));
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
