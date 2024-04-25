package com.example.demo.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Order;
import com.example.demo.services.OrderService;

@Controller
@RequestMapping("/admin")
public class OrderMController {
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("order")
	public String orders(Model model) {
		
		List<Order> orders = this.orderService.getAll();
		
		model.addAttribute("orders", orders);
		return "admin/order/index";
	}
	
}
