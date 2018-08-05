package com.example.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer>{
	public OrderDetail findByorderId(Integer ids);
//	public List<OrderDetail> save(List<OrderDetail> orderDetail);
	@SuppressWarnings("unchecked")
	public OrderDetail save(OrderDetail orderDetail);
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO order_detail(order_id,item_id,product_id,status) VALUES(:orderId,:itemId,:prodId,:status)", nativeQuery = true)
	public void saveOrderDetail(@Param(value = "orderId") int orderId,@Param(value = "itemId") int itemId,@Param(value = "prodId") int prodId,@Param(value = "status") String status);
	@Query(value = "SELECT item_id FROM order_detail ORDER BY item_id DESC LIMIT 1;" , nativeQuery = true)
	public Integer findLastitemId();
	@Query(value = "SELECT * FROM order_detail WHERE order_id = :orderId" , nativeQuery = true)
	public List<OrderDetail> findAllByorderIds(@Param(value = "orderId") Integer orderId);
}
