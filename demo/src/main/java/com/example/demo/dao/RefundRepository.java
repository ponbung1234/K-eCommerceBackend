package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Refund;

public interface RefundRepository extends JpaRepository<Refund, Integer> {
	@SuppressWarnings("unchecked")
	public Refund save(Refund refund);
}
