package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Cart;
import com.example.demo.models.CartItem;
import com.example.demo.models.User;
import com.example.demo.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;
	@Override
	public Boolean addCart(Cart cart) {
		// TODO Auto-generated method stub
		try {
			this.cartRepository.save(cart);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return false;
	}
	@Override
	public Integer checkE(Long idUser) {
		// TODO Auto-generated method stub
		return this.cartRepository.countId(idUser);
	}
	@Override
	public Cart finbyUser(User user) {
		// TODO Auto-generated method stub
		return cartRepository.findByUser(user).get(0);
	}
	@Override
	public List<CartItem> findByCartId(Integer cartId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
