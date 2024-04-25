package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Order;

public interface OrderService {
	List<Order> getAll();
	Boolean create(Order order);
}
