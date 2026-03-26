package com.learning.pilot.service;

import com.learning.pilot.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final List<Order> orders = new ArrayList<>();

    public List<Order> getAllOrders() {
        return orders;
    }

    public Optional<Order> getOrderById(String id) {
        return orders.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst();
    }

    public Order createOrder(Order order) {
        orders.add(order);
        return order;
    }

    public Optional<Order> updateOrder(String id, Order updatedOrder) {
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getId().equals(id)) {
                updatedOrder.setId(id);
                orders.set(i, updatedOrder);
                return Optional.of(updatedOrder);
            }
        }
        return Optional.empty();
    }

    public boolean deleteOrder(String id) {
        return orders.removeIf(o -> o.getId().equals(id));
    }
    
}
