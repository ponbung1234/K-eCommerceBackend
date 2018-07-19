package com.example.demo.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Products;
import com.example.service.ProductService;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

@RestController
@RequestMapping("/products")
@CrossOrigin(allowedHeaders = "*")
public class ProductController {
	@Autowired
	private ProductService productService;

	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getSearchResult() throws IOException {
		Iterator<Products> itr = productService.getAllProducts().iterator();
		JsonFactory jsonFactory = new JsonFactory();
		ByteArrayOutputStream json_temp = new ByteArrayOutputStream();
		JsonGenerator jsonGenerator = jsonFactory.createGenerator(json_temp, JsonEncoding.UTF8);

		jsonGenerator.writeRaw('[');
		while (itr.hasNext()) {
			Products tempp = itr.next();
			jsonGenerator.writeStartObject();
			jsonGenerator.writeNumberField("product_id", tempp.getProduct_id());
			jsonGenerator.writeNumberField("category_id", tempp.getCategory_id());
			jsonGenerator.writeStringField("product_name", tempp.getProduct_name());
			jsonGenerator.writeNumberField("price", tempp.getPrice());
			jsonGenerator.writeNumberField("product_amount", tempp.getProduct_amount());
			jsonGenerator.writeStringField("url", tempp.getPro_image_path());
			jsonGenerator.writeStringField("product_description", tempp.getProduct_description());
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

// JsonFactory jsonFactory = new JsonFactory();
// ByteArrayOutputStream json_temp = new ByteArrayOutputStream();
// JsonGenerator jsonGenerator = jsonFactory.createGenerator(json_temp,
// JsonEncoding.UTF8);
// Database db = new Database();
// Connection con = db.connect();
// Statement stm = con.createStatement();
//
// ResultSet results;
// results = stm.executeQuery("SELECT * FROM products");
//
// jsonGenerator.writeRaw('[');
// results.beforeFirst();
// while (results.next()) {
// jsonGenerator.writeStartObject();
// jsonGenerator.writeNumberField("product_id", results.getInt(1));
// jsonGenerator.writeNumberField("category_id", results.getInt(2));
// jsonGenerator.writeStringField("product_name", results.getString(3));
// jsonGenerator.writeNumberField("price", results.getDouble(4));
// jsonGenerator.writeNumberField("product_amount", results.getInt(5));
// jsonGenerator.writeStringField("url", results.getString(6));
// jsonGenerator.writeStringField("product_description", results.getString(7));
//
// jsonGenerator.writeEndObject();
// if (!results.isLast()) {
// jsonGenerator.writeRaw(',');
// jsonGenerator.writeRaw('\n');
// }
// }
// jsonGenerator.writeRaw(']');
// jsonGenerator.close();
// db.close();
//
// return json_temp.toString();