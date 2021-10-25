package com.springboot.orderservice.service;

import com.springboot.orderservice.domain.Order;
import com.springboot.orderservice.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}
