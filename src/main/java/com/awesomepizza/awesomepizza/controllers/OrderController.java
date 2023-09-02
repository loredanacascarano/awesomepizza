package com.awesomepizza.awesomepizza.controllers;

import com.awesomepizza.awesomepizza.exceptions.OrderNotFoundException;
import com.awesomepizza.awesomepizza.models.Order;
import com.awesomepizza.awesomepizza.models.StatusEnum;
import com.awesomepizza.awesomepizza.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @PostMapping("/neworder")
    public Order createOrder(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @PutMapping("/aggiornaOrdine")
    public Order updateOrderStatus(@RequestParam Long id) throws Throwable {
        Order existingOrder = orderRepository.findById(id).orElseThrow(new OrderNotFoundException("Ordine non trovato"));
        existingOrder.setStatus(existingOrder.getStatus().nextStatus());

        return orderRepository.save(existingOrder);
    }

}
