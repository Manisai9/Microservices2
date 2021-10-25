package com.springboot.orderservice.feignclient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springboot.orderservice.domain.Products;


public interface ProductClient {

    @GetMapping(value = "/products/{id}")
    public Products getProductById(@PathVariable(value = "id") Long productId);

}
