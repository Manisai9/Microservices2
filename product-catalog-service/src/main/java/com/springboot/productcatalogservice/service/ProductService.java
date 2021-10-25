package com.springboot.productcatalogservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.productcatalogservice.entity.Product;
import com.springboot.productcatalogservice.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

 
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

 
    public List<Product> getAllProductByCategory(String category) {
        return productRepository.findAllByCategory(category);
    }

  
    public Product getProductById(Long id) {
        return productRepository.getOne(id);
    }

   
      
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

   
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
