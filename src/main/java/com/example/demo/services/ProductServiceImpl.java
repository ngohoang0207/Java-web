package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.models.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Override
	public List<Product> getAll() {
		// TODO Auto-generated method stub
//		return this.productRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
		return this.productRepository.findAll();
	}

	@Override
	public Boolean create(Product product) {
		// TODO Auto-generated method stub
		try {
			this.productRepository.save(product);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Product findById(Integer id) {
		
		return this.productRepository.findById(id).get();
	}

	@Override
	public Boolean update(Product product) {
		// TODO Auto-generated method stub
		try {
			this.productRepository.save(product);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductNew() {
		// TODO Auto-generated method stub
		return this.productRepository.getProductNew();
	}

}
