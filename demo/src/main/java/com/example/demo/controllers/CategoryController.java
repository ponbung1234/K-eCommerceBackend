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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Database;
import com.example.demo.model.Category;
import com.example.demo.model.Products;
import com.example.service.CategoryService;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;


@RestController
@RequestMapping("/category")
@CrossOrigin(allowedHeaders = "*")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;


	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getSearchResult() throws IOException {

		Iterator<Category> itr = categoryService.getAllCategory().iterator();
		JsonFactory jsonFactory = new JsonFactory();
		ByteArrayOutputStream json_temp = new ByteArrayOutputStream();
		JsonGenerator jsonGenerator = jsonFactory.createGenerator(json_temp, JsonEncoding.UTF8);
	
		jsonGenerator.writeRaw('[');
		while (itr.hasNext()) {
			Category tempp = itr.next();
			jsonGenerator.writeStartObject();
			jsonGenerator.writeNumberField("category_id", tempp.getCategory_id());
			jsonGenerator.writeStringField("category_name", tempp.getCategory_name());

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