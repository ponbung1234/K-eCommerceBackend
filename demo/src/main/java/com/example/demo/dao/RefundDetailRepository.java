package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.RefundDetail;

public interface RefundDetailRepository extends JpaRepository<RefundDetail,Integer>{
	public List<RefundDetail> findAllByid(List<Integer> product_ids);
	public RefundDetail findAllByid(Integer product_ids);

}
