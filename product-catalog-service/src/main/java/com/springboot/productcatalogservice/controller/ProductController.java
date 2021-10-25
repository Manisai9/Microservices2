package com.springboot.productcatalogservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.productcatalogservice.entity.Product;
import com.springboot.productcatalogservice.http.header.HeaderGenerator;
import com.springboot.productcatalogservice.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private HeaderGenerator headerGenerator;
	
	@GetMapping(value = "/products", params = "category")
	public ResponseEntity<List<Product>> getAllProductByCategory(@RequestParam("category") String category) {
		List<Product> products = productService.getAllProductByCategory(category);
		if (!products.isEmpty()) {
			return new ResponseEntity<List<Product>>(products, headerGenerator.getHeadersForSuccessGetMethod(),
					HttpStatus.OK);
		}
		return new ResponseEntity<List<Product>>(headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value = "/products/{id}")
	public ResponseEntity<Product> getOneProductById(@PathVariable("id") long id) {
		Product product = productService.getProductById(id);
		if (product != null) {
			return new ResponseEntity<Product>(product, headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);
		}
		return new ResponseEntity<Product>(headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);

	}
	

}
