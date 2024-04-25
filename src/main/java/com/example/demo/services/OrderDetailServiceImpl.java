package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Order;
import com.example.demo.models.OrderDetail;
import com.example.demo.repository.OrderDetailRepository;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	private OrderDetailRepository orderDetailRepository;
	@Override
	public Boolean add(OrderDetail detail) {
		// TODO Auto-generated method stub
		try {
			this.orderDetailRepository.save(detail);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;
	}


	
}
