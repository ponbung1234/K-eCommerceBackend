package com.example.demo.controllers;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Card;
import com.example.demo.model.Pay;

@RestController
@RequestMapping("/prepay")
@CrossOrigin(allowedHeaders = "*")
public class ChargeController {
	String apiKey = "skey_test_38jslQmagN2yMyVtyRxAf4LQv8SvF29tgz";
	String url = "https://dev-kpaymentgateway-services.kpgwlive.com/tokens";

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody String pay(@RequestHeader(name = "x-api-key") String apikey, @RequestBody Pay pay) throws JSONException {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("x-api-key", apikey);
		Card card = new Card("K. SOMRAK","4921412100010391","12","2017","123");
		JSONObject json = new JSONObject();
		//json.put("card", card);
		json.put("name", card.getName());
		json.put("number", card.getNumber());
		json.put("expmonth", card.getExpmonth());
		json.put("expyear", card.getExpyear());
		json.put("cvv", card.getCvv());
		String jsonn = json.toString();

		HttpEntity<String> request = new HttpEntity<String>("{\"card\":"+jsonn +"}", headers);
		

		System.out.println("body : " + request.getBody());
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

		System.out.println(response.getBody());
		return response.getBody();
	}

}
