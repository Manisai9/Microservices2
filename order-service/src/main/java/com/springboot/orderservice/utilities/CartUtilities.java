package com.springboot.orderservice.utilities;

import java.math.BigDecimal;

import com.springboot.orderservice.domain.Products;

public class CartUtilities {

    public static BigDecimal getSubTotalForItem(Products product, int quantity){
       return (product.getPrice()).multiply(BigDecimal.valueOf(quantity));
    }
}
