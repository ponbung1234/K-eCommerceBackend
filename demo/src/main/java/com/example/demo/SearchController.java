package com.example.demo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

@RestController
@RequestMapping("/search")
@CrossOrigin(allowedHeaders="*")
public class SearchController {

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getSearchResult(@RequestParam(value = "input", required = true) String input)
			throws SQLException, IOException {
		JsonFactory jsonFactory = new JsonFactory();
		ByteArrayOutputStream json_temp = new ByteArrayOutputStream();
		JsonGenerator jsonGenerator = jsonFactory.createGenerator(json_temp, JsonEncoding.UTF8);
		Database db = new Database();
		Connection con = db.connect();
		Statement stm = con.createStatement();
		ResultSet temp = stm.executeQuery("SELECT * FROM product_categories WHERE category_name = '" + input + "'");

		ResultSet results;
		if (!temp.next()) {
			results = stm.executeQuery("SELECT * FROM products WHERE product_name = '" + input + "'");

		} else {

			temp.first();
			results = stm.executeQuery("SELECT * FROM products WHERE category_id = " + temp.getInt(1));

		}
		if(!results.next()) {
			return "try a different keyword";
		}
		jsonGenerator.writeRaw('[');
		results.beforeFirst();
		while (results.next()) {
			jsonGenerator.writeStartObject();
			jsonGenerator.writeNumberField("product_id", results.getInt(1));
			jsonGenerator.writeNumberField("category_id", results.getInt(2));
			jsonGenerator.writeStringField("product_name", results.getString(3));
			jsonGenerator.writeNumberField("price", results.getDouble(4));
			jsonGenerator.writeNumberField("product_amount", results.getInt(5));
			jsonGenerator.writeStringField("url", results.getString(6));
			jsonGenerator.writeEndObject();
			if (!results.isLast()) {
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