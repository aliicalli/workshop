package com.alicalli.orderservice.repository;

import com.alicalli.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
  
}
