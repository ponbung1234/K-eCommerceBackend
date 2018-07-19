package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.RefundRepository;
import com.example.demo.model.Refund;

@Service
public class RefundService {
	@Autowired
	private RefundRepository refRep;
	
	public void saveRefund(Refund refund) {
		refRep.save(refund);
		System.out.println("refund saved");
	}
}
