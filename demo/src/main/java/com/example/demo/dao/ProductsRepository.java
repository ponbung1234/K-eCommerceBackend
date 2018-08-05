package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.example.demo.model.Products;

@Component
public interface ProductsRepository extends JpaRepository<Products,Integer>{
	public Products findById(int id);
	public List<Products> findAll();
}
