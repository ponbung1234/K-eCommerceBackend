package com.example.demo;

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

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

@RestController
@RequestMapping("/refund")
@CrossOrigin(allowedHeaders="*")
public class RefundController {
//select item_id,orders.order_id, product_name,price,pro_image_path,product_description, date from products,order_detail,orders 
//	where order_detail.product_id = products.product_id AND orders.order_id = order_detail.order_id AND orders.ecustomer_id =1;
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String getRefund()
			throws SQLException, IOException {

		JsonFactory jsonFactory = new JsonFactory();
		ByteArrayOutputStream json_temp = new ByteArrayOutputStream();
		JsonGenerator jsonGenerator = jsonFactory.createGenerator(json_temp, JsonEncoding.UTF8);
		Database db = new Database();
		Connection con = db.connect();
		Statement stm = con.createStatement();
		
		ResultSet results;
		results = stm.executeQuery("select item_id,orders.order_id, product_name,price,pro_image_path,product_description, date,products.product_id from products,order_detail,orders \n" + 
				"where order_detail.product_id = products.product_id AND orders.order_id = order_detail.order_id AND orders.ecustomer_id =1;");
		//select order_detail.item_id,orders.order_id, products.product_name,products.price,products.pro_image_path,products.product_description,orders.date,products.product_id from products natural join order_detail natural join orders" + 
		//"where order_detail.product_id = products.product_id AND orders.order_id = order_detail.order_id AND orders.ecustomer_id =1;
		jsonGenerator.writeRaw('[');
		results.beforeFirst();
		while (results.next()) {
			jsonGenerator.writeStartObject();
			jsonGenerator.writeNumberField("item_id", results.getInt(1));
			jsonGenerator.writeNumberField("order_id", results.getInt(2));
			jsonGenerator.writeStringField("product_name", results.getString(3));
			jsonGenerator.writeNumberField("price", results.getDouble(4));
			jsonGenerator.writeStringField("pro_image_path", results.getString(5));
			jsonGenerator.writeStringField("product_description", results.getString(6));
			jsonGenerator.writeStringField("date", results.getString(7));
			jsonGenerator.writeNumberField("product_id", results.getInt(8));

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
