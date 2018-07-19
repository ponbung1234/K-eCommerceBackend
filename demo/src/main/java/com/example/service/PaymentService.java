package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.PaymentRepository;

import com.example.demo.model.Payment;

@Service
public class PaymentService {
	@Autowired
	private PaymentRepository paymentRep;
	
	public List<Payment> findOrderById(int id){
		return paymentRep.findByorderID(id);
	}

}
