package com.example.demo.services;

import com.example.demo.models.ImageProduct;

public interface ImageProductService  {

	Boolean create(ImageProduct imageProduct);
	Boolean deleteByProductId(Integer id);
}
