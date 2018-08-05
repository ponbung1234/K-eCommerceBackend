package com.example.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Cart;

public interface CartRepository extends JpaRepository<Cart,Integer>{
	@Query(value = "SELECT * FROM cart WHERE ecustomer_id =?1", nativeQuery = true)
	public List<Cart> findAllByecustId(int id);
	public List<Cart> findById(int cart_id);
	public List<Cart> findAll();
	@SuppressWarnings("unchecked")
	public Cart save(Cart cart);
	
	
	
	
	@Modifying
	@Transactional
	@Query("DELETE Cart WHERE id = ?1 and ecustId = ?2 and product_id = ?3")
	public void delete(int cart_id,int e_custid,int product_id);
	
	@Modifying
	@Transactional
	@Query("DELETE Cart WHERE ecustId = ?1")
	public void deleteAll(int e_custid);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO cart VALUES(:cart_id,:ecustomer_id,:product_id,:cart_amount)", nativeQuery = true)
	public void save(@Param("cart_id") int cart_id,@Param("ecustomer_id") int e_custid,@Param("product_id") int product_id,@Param("cart_amount") int cart_amount);
}
