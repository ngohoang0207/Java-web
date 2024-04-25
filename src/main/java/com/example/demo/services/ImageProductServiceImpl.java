package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.ImageProduct;
import com.example.demo.repository.ImageProductRepository;

@Service
public class ImageProductServiceImpl implements ImageProductService {

	@Autowired
	ImageProductRepository imageProductRepository;
	@Override
	public Boolean create(ImageProduct imageProduct) {
		// TODO Auto-generated method stub
		try {
			this.imageProductRepository.save(imageProduct);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean deleteByProductId(Integer id) {
		
		try {
			this.imageProductRepository.deleteByProductId(id);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
}
