package com.example.demo.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Payment;
import com.example.demo.model.RefundDetail;

public interface PaymentRepository extends JpaRepository<Payment,Integer>{
	public List<Payment> findByorderID(List<Integer> order_ids);
	public Payment findByorderID(Integer order_ids);
}
