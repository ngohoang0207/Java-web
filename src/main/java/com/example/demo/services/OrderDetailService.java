package com.example.demo.services;

import java.util.List;

import com.example.demo.models.CartItem;
import com.example.demo.models.Order;
import com.example.demo.models.OrderDetail;

public interface OrderDetailService {
	Boolean add(OrderDetail detail);
}
