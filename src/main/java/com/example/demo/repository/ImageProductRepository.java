package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.models.ImageProduct;
import com.example.demo.models.Product;

import jakarta.transaction.Transactional;

@Transactional
public interface ImageProductRepository extends JpaRepository<ImageProduct,Integer> {
	
	@Modifying
	@Query("delete from ImageProduct i where i.product.id=?1")
	void deleteByProductId (Integer id); 
}
