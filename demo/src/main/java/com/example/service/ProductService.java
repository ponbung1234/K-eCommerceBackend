package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProductsRepository;
import com.example.demo.model.Products;

@Service
public class ProductService {
	@Autowired
	private ProductsRepository productsRepository;
	
	public List<Products> getAllProducts() {
		return productsRepository.findAll();
	}
}
