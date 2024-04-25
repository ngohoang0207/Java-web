package com.example.demo.services;

import com.example.demo.models.CartItem;

public interface CartItemService {
	
	Boolean add(CartItem cartItem);
	CartItem update(CartItem cartItem);
	CartItem findId(Integer id);
	Boolean delete(Integer id);
	CartItem checkCartItem(Integer cartId,Integer productId);
	void deleteByCartId(Integer cartId);
}
