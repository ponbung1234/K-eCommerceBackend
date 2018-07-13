package com.example.demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postRefund")
@CrossOrigin(allowedHeaders = "*")
public class postRefund {
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String refund(@RequestBody String refund) throws SQLException, IOException {

		String[] temp = refund.split(",");
		int item_id = Integer.parseInt(temp[0]);
		int amount = Integer.parseInt(temp[1]);
		String reasonn = temp[2];
		int order_id = Integer.parseInt(temp[3]);
		int product_id = Integer.parseInt(temp[4]);

		Database db = new Database();
		Connection con = db.connect();
		Statement stm = con.createStatement();

		stm.executeUpdate("update order_detail set status = \"Requested\" where item_id =" + item_id);
		stm.executeUpdate("insert into refund(ecustomer_id,order_id,ref_product_id,date,reason,refundStatus)"
				+ "values(1," + order_id + "," + product_id + ",SYSDATE(),\"" + reasonn + "\",\"requested\")");

		db.close();

		return "Action sucessful";

	}
}
