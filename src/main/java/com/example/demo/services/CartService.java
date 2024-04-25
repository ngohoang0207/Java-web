package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Cart;
import com.example.demo.models.CartItem;
import com.example.demo.models.User;

public interface CartService {
	Boolean addCart(Cart cart);
	Integer checkE(Long idUser);
	Cart finbyUser(User user);
	List<CartItem> findByCartId(Integer cartId);
	
}
