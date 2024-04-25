package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Order;
import com.example.demo.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Override
	public List<Order> getAll() {
		// TODO Auto-generated method stub
		return this.orderRepository.findAll();
	}

	@Override
	public Boolean create(Order order) {
		// TODO Auto-generated method stub
		try {
			this.orderRepository.save(order);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return false;
	}


}
