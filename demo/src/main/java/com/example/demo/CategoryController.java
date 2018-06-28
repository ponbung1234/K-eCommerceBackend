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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;


@RestController
@RequestMapping("/category")
public class CategoryController {


	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getSearchResult()
			throws SQLException, IOException {

		JsonFactory jsonFactory = new JsonFactory();
		ByteArrayOutputStream json_temp = new ByteArrayOutputStream();
		JsonGenerator jsonGenerator = jsonFactory.createGenerator(json_temp, JsonEncoding.UTF8);
		Database db = new Database();
		Connection con = db.connect();
		Statement stm = con.createStatement();
		
		ResultSet results;
		results = stm.executeQuery("SELECT * FROM Product_categories");

		jsonGenerator.writeRaw('[');
		results.beforeFirst();
		while (results.next()) {
			jsonGenerator.writeStartObject();
			jsonGenerator.writeNumberField("category_id", results.getInt(1));
			jsonGenerator.writeStringField("category_name", results.getString(2));
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