package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Integer>{
	public Payment findBypaymentId(int payment_ids);
	public Payment findByorderId(int order_id);
}
