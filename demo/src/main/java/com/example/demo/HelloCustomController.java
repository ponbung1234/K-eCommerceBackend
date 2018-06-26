package com.example.demo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello-custom")
public class HelloCustomController {
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();


	@RequestMapping(method=RequestMethod.POST)
	public @ResponseBody Greeting sayHello(@RequestParam(value="name", required=true, defaultValue="Stranger") String name) throws SQLException {
		if(!name.equals("Stranger")) {
			String n = name;
			Database db = new Database();
			Connection con = db.connect();
			Statement s = con.createStatement();
			s.executeUpdate("INSERT INTO test1(name1) value(\"" + n+"\")");
			System.out.println("INSERTED : " + n);
			db.close();
		}
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
		
	}
}
