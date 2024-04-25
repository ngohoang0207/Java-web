package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.ImageProduct;
import com.example.demo.models.Product;
import com.example.demo.services.ProductService;

@Controller
public class HomeProductController {

	@Autowired
	private ProductService productService;
	@RequestMapping("/detail/{id}")
	
	public String detail(@PathVariable("id") Integer id,Model model) {
		
		Product product =  this.productService.findById(id);
		
		model.addAttribute("product", product);
		List<String> imgDetail = new ArrayList<String>();
		for (ImageProduct imgPro : product.getImageProduct() ) {
			imgDetail.add(imgPro.getImage());
		}
		model.addAttribute("imgDetail", imgDetail);
		return "detail";
	}
}
