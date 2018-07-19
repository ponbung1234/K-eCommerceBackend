package com.example.demo.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Database;

@RestController
@RequestMapping("/addCart")
@CrossOrigin(allowedHeaders="*")
public class AddCartController {
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String cart(@RequestBody String cart) throws SQLException, IOException {
	
		//Product_id,amount
		String[] temp = cart.split(",");
		int product_id = Integer.parseInt(temp[0]);
		int amount = Integer.parseInt(temp[1]);
		Database db = new Database();
		Connection con = db.connect();
		Statement stm = con.createStatement();
		ResultSet results;
		results = stm.executeQuery("select product_id,cart_amount from cart where ecustomer_id = 1;");
		results.beforeFirst();
		int flag = 0;
		int newCartAmount;
		while (results.next()) {
			stm = con.createStatement();
			if(product_id == results.getInt(1)) {
				//update
				newCartAmount = results.getInt(2);
				newCartAmount += amount;
				stm.executeUpdate("update cart set cart_amount = "+newCartAmount+" where product_id =" + product_id);
				flag=1;
			}
		}
		if(flag == 0) {
			//insert
			stm = con.createStatement();
			stm.executeUpdate("insert into cart(ecustomer_id,product_id,cart_amount)"
					+ "values(1," + product_id + ", "+amount +" )");
		}

	

		db.close();

		return "Action sucessful";
	}
}
