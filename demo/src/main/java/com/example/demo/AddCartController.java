package com.example.demo;

import java.io.IOException;
import java.sql.Connection;
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
	public @ResponseBody void getOrder(@RequestParam(value = "ecustomer_id", required = true)
			String ecus_id,@RequestParam(value = "product_id", required = true) String product_id)
			throws IOException, SQLException {
		Database db = new Database();
		Connection con = db.connect();
		Statement stm = con.createStatement();
		stm.executeUpdate("insert into cart(ecustomer_id,product_id,cart_amount) values("
		+Integer.parseInt(ecus_id)+"," + Integer.parseInt(product_id)+","+"1)");
		db.close();
	}
}
