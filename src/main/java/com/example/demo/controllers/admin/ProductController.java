package com.example.demo.controllers.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.models.Category;
import com.example.demo.models.ImageProduct;
import com.example.demo.models.Product;
import com.example.demo.services.CategoryService;
import com.example.demo.services.ImageProductService;
import com.example.demo.services.ProductService;
import com.example.demo.services.StorageService;

@Controller
@RequestMapping("/admin")
public class ProductController {
	
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private StorageService storageService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired 
	private ImageProductService imageProductService;
	
	@RequestMapping("/product")
	public String index(Model model) {
		
		List<Product> listCategory = this.productService.getAll();
		
		model.addAttribute("listCategory",listCategory );
		return "admin/product/index";
	}
	
	@RequestMapping("/product-add")
	public String add(Model model) {
		
		Product product = new Product();
		model.addAttribute("product", product);
		
		List<Category> listCate = this.categoryService.getAll();
		model.addAttribute("listCate", listCate);
		
		
		return "admin/product/add";
	}
	
	@PostMapping("/product-add")
	
	public String save(@ModelAttribute("product") Product product,@RequestParam("fileImage") MultipartFile file,@RequestParam("fileImages") MultipartFile[] files) {
		
		// upload file
		
		this.storageService.store(file);
		
		String fileName = file.getOriginalFilename();
		product.setImage(fileName);
		if(this.productService.create(product)) {
			 
			//upload anh mo ta
			for (MultipartFile multipartFile : files) {
					ImageProduct imageProduct = new ImageProduct();
					imageProduct.setImage(multipartFile.getOriginalFilename());
					imageProduct.setProduct(product);
					this.storageService.store(multipartFile);
					this.imageProductService.create(imageProduct);
			}
			
			
//					this.proUpload();
			return "redirect:/admin/product";
			
			
		}
		return "admin/product/add";
	}
	
	
	@GetMapping("/edit-product/{id}")
	public String edit(Model model,@PathVariable("id") Integer id) {
		
		Product product = this.productService.findById(id);
		model.addAttribute("product", product);
		List<Category> listCate = this.categoryService.getAll();
		model.addAttribute("listCate", listCate);
		List<String> imgDetail = new ArrayList<String>();
		for (ImageProduct imgPro : product.getImageProduct() ) {
			imgDetail.add(imgPro.getImage());
		}
		model.addAttribute("imgDetail", imgDetail);
		return "admin/product/edit";
		
	}
	
	@PostMapping("/product-edit")
	
public String update(@ModelAttribute("product") Product product,@RequestParam("fileImage") MultipartFile file,@RequestParam("fileImages") MultipartFile[] files) {
		
		
		String fileName = file.getOriginalFilename();
		boolean isEmpty = fileName == null || fileName.trim().length() == 0;
		if(!isEmpty) {
			// upload file
			this.storageService.store(file);
			product.setImage(fileName);
		}
		
		String fileName1 = files[0].getOriginalFilename();
		
		boolean isEmpty1 = fileName1 == null || fileName1.trim().length() == 0;
		
		if(!isEmpty1) {
			// xóa tất cả bản ghi ở bảng productImage theo id product
			this.imageProductService.deleteByProductId(product.getId());
			for (MultipartFile multipartFile : files) {
				
				// upload lại ảnh và thêm mới lại
				ImageProduct imageProduct = new ImageProduct();
				imageProduct.setImage(multipartFile.getOriginalFilename());
				imageProduct.setProduct(product);
				this.storageService.store(multipartFile);
				this.imageProductService.create(imageProduct);
			}
		}
		

		if(this.productService.update(product)) {
			return "redirect:/admin/product";
		}
		return "admin/product/edit";
	}
}
