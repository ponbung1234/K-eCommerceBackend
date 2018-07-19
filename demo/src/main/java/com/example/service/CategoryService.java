package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CategoryRepository;
import com.example.demo.model.Category;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	public List<Category> getAllCategory() {
		return categoryRepository.findAll();
	}
	
}
