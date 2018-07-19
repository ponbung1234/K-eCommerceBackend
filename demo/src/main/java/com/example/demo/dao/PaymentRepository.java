package com.example.demo.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Payment;

public interface PaymentRepository extends JpaRepository<Payment,Integer>{
	public List<Payment> findByorderID(int id);
}
