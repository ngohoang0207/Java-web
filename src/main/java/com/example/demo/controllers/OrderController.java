package com.example.demo.controllers;

import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Cart;
import com.example.demo.models.CartItem;
import com.example.demo.models.CustomUserDetails;
import com.example.demo.models.Order;
import com.example.demo.models.OrderDetail;
import com.example.demo.services.CartItemService;
import com.example.demo.services.CartService;
import com.example.demo.services.OrderDetailService;
import com.example.demo.services.OrderService;
import com.example.demo.services.ProductService;
@Controller
public class OrderController {
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private CartService cartService;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderService orderService;
	@Autowired OrderDetailService orderDetailService;
	
	@RequestMapping("checkout")
	public String checkout(Principal principal,org.springframework.ui.Model model) {
		
		if(principal == null) {
			return "redirect:/login";
		}
		CustomUserDetails customUserDetails =  (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Cart cart = this.cartService.finbyUser(customUserDetails.getUser());
		
		model.addAttribute("user",customUserDetails.getUser());
		model.addAttribute("listCart", cart);
		model.addAttribute("totalPrice", cart.totalPrice());
		Order order = new Order();
		order.setUser(customUserDetails.getUser());
		model.addAttribute("order", order);
		
		
		return "checkout";
	}
	
	@RequestMapping("postCheckout")
	public String postcheckout(Principal principal,@ModelAttribute("order") Order order) {
		
		if(principal == null) {
			return "redirect:/login";
		}
		CustomUserDetails customUserDetails =  (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Cart cart = this.cartService.finbyUser(customUserDetails.getUser());
		order.setUser(customUserDetails.getUser());
		order.setCreatedAt(new Date());
		order.setStatus(0);
		if(this.orderService.create(order)) {
			for (CartItem cartItem : cart.getCartItems()) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setOrder(order);
				orderDetail.setPrice(cartItem.getProduct().getPrice());
				orderDetail.setProduct(cartItem.getProduct());
				orderDetail.setQuantity(cartItem.getQuantity());
				this.orderDetailService.add(orderDetail);
				
			}
			
		}
		
		this.cartItemService.deleteByCartId(cart.getId());
		
		return "camon";
	}
}
