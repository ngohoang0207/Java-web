package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.models.Category;
import com.example.demo.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer >{
	@Query("SELECT c FROM Product c ORDER BY c.id DESC LIMIT 2")
	List<Product> getProductNew();
}
