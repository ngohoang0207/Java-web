package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.Category;
import com.example.demo.models.Product;
import com.example.demo.services.CategoryService;
import com.example.demo.services.ProductService;

@Controller
public class HomeController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("")
	
	public String home(Model model) {
		
		List<Category> categories = categoryService.getAllByStatus();
		model.addAttribute("listCate", categories);	
		
		List<Product> products = productService.getAll();
		model.addAttribute("productAll", products);
		
		List<Product> productNews = productService.getProductNew();
		model.addAttribute("productNews", productNews);
		return "index";
	}
}
