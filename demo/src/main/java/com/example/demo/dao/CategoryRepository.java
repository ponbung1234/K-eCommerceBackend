package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer>{
	public Category findBycategoryID(int id);
	public List<Category> findAll();
}
