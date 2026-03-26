package com.learning.pilot.controller;

import com.learning.pilot.model.Order;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    // In-memory list to store orders
    private List<Order> orders = new ArrayList<>();

    // GET all orders
    @GetMapping
    public List<Order> getAllOrders() {
        return orders;
    }

    // GET a single order by ID
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable String id) {
        return orders.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // POST - create a new order
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        orders.add(order);
        return order;
    }

}