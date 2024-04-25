package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.CartItem;
import com.example.demo.repository.CartItemRepository;

@Service
public class CartItemServiceImpl implements CartItemService {
	@Autowired
	private CartItemRepository cartItemRepository;
	@Override
	public Boolean add(CartItem cartItem) {
		// TODO Auto-generated method stub
		try {
			this.cartItemRepository.save(cartItem);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;
	}

	@Override
	public CartItem update(CartItem cartItem) {
		// TODO Auto-generated method stub
		return this.cartItemRepository.save(cartItem);
	}

	@Override
	public CartItem findId(Integer id) {
		// TODO Auto-generated method stub
		return this.cartItemRepository.findById(id).get();
	}

	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CartItem checkCartItem(Integer cartId, Integer productId) {
		// TODO Auto-generated method stub
		return this.cartItemRepository.findByCartIdAndProductId(cartId,productId);
	}

	@Override
	public void deleteByCartId(Integer cartId) {
		// TODO Auto-generated method stub
		this.cartItemRepository.deleteByCartId(cartId);
	}

}
