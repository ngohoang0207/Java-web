package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Order;
import com.example.demo.models.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
}
