package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    @Transactional
    public Order createOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        orderRepository.flush(); // Explicitly flush changes to the database
        return savedOrder;
    }

   

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    public void deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
        } else {
            throw new OrderNotFoundException("Order with id " + id + " not found");
        }
    }
    
    
}
