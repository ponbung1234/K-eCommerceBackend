package com.example.demo;

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

@RestController
@RequestMapping("/addCart")
@CrossOrigin(allowedHeaders="*")
public class AddCartController {
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String cart(@RequestBody int cart) throws SQLException, IOException {
	
		int product_id = cart;
		Database db = new Database();
		Connection con = db.connect();
		Statement stm = con.createStatement();
		
		ResultSet results;
		results = stm.executeQuery("select product_id,cart_amount from cart where ecustomer_id = 1;");
		results.beforeFirst();
		int flag = 0;
		int newCartAmount;
		while (results.next()) {
			if(product_id == results.getInt(1)) {
				//update
				newCartAmount = results.getInt(2);
				newCartAmount++;
				stm.executeUpdate("update cart set cart_amount = \"newCartAmount\" where product_id =" + product_id);
				flag=1;
			}
		}
		if(flag == 1) {
			//insert
			stm.executeUpdate("insert into cart(ecustomer_id,product_id,cart_amount)"
					+ "values(1," + product_id + ", 1 )");
		}

	

		db.close();

		return "Action sucessful";
	}
}
