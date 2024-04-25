package com.example.demo.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Cart;
import com.example.demo.models.CartItem;
import com.example.demo.models.CustomUserDetails;
import com.example.demo.services.CartItemService;
import com.example.demo.services.CartService;
import com.example.demo.services.ProductService;

import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private CartService cartService;
	@Autowired
	private ProductService productService;
	@GetMapping
	public String showCart(Principal principal,org.springframework.ui.Model model) {
		
		if(principal == null) {
			return "redirect:/login";
		}
		CustomUserDetails customUserDetails =  (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Cart cart = this.cartService.finbyUser(customUserDetails.getUser());
		
		
		model.addAttribute("listCart", cart);
		return "cart";
	}
	
	@PostMapping
	public String addCart(@RequestParam("id") Integer idProduct,@RequestParam("quantity") Integer quantity,Principal principal) {
		
		if(principal == null) {
			return "redirect:/login";
		}
		Cart cart = new Cart();
		CustomUserDetails customUserDetails =  (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		CartItem cartItem = new CartItem();
		//kiểm tra id của user có trong db chưa
		
		cart.setUser(customUserDetails.getUser());
		
		if(this.cartService.checkE(customUserDetails.getUser().getId()) == 0) {
			this.cartService.addCart(cart);
			cartItem.setCart(cart);
		} else {
			Cart cart1 = this.cartService.finbyUser(customUserDetails.getUser());
			
			cartItem.setCart(cart1);
		}
		
		//kiểm tra xem sản phẩm đã tồn tại hay là chưa
		CartItem cartItemcheck = this.cartItemService.checkCartItem(cartItem.getCart().getId(), idProduct);
		if(cartItemcheck != null) {
			
			cartItemcheck.setQuantity(cartItemcheck.getQuantity()+quantity);
			this.cartItemService.add(cartItemcheck);
		}else {
			cartItem.setProduct(this.productService.findById(idProduct));
			cartItem.setQuantity(quantity);
			this.cartItemService.add(cartItem);
		}
		
		
		
		
		return "redirect:/cart";
	}
	
	
	@RequestMapping("checkout")
	public String checkout(Principal principal,org.springframework.ui.Model model) {
		
		if(principal == null) {
			return "redirect:/login";
		}
		CustomUserDetails customUserDetails =  (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Cart cart = this.cartService.finbyUser(customUserDetails.getUser());
		
		
		model.addAttribute("listCart", cart);
		return "checkout";
	}
}
