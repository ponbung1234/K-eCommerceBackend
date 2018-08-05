package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
	@Query(value = "SELECT * FROM ecustomers WHERE username = :name" , nativeQuery = true)
    Users findByName(@Param(value = "name")String username);
}
