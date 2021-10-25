package com.springboot.productcatalogservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.productcatalogservice.entity.Product;
import com.springboot.productcatalogservice.http.header.HeaderGenerator;
import com.springboot.productcatalogservice.service.ProductService;

@RestController
@RequestMapping("/admin")
public class AdminProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private HeaderGenerator headerGenerator;

	@PostMapping(value = "/products")
	private ResponseEntity<Product> addProduct(@RequestBody Product product, HttpServletRequest request) {
		if (product != null) {
			try {
				productService.addProduct(product);
				return new ResponseEntity<Product>(product,
						headerGenerator.getHeadersForSuccessPostMethod(request, product.getId()), HttpStatus.CREATED);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Product>(headerGenerator.getHeadersForError(),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<Product>(headerGenerator.getHeadersForError(), HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(value = "/products/{id}")
	private ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
		Product product = productService.getProductById(id);
		if (product != null) {
			try {
				productService.deleteProduct(id);
				return new ResponseEntity<Void>(headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Void>(headerGenerator.getHeadersForError(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<Void>(headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/products")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProduct();
		if (!products.isEmpty()) {
			return new ResponseEntity<List<Product>>(products, headerGenerator.getHeadersForSuccessGetMethod(),
					HttpStatus.OK);
		}
		return new ResponseEntity<List<Product>>(headerGenerator.getHeadersForError(), HttpStatus.NOT_FOUND);
	}

	
}
