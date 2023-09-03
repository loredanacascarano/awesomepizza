package com.awesomepizza.awesomepizza.controllers;

import com.awesomepizza.awesomepizza.exceptions.OrderNotFoundException;
import com.awesomepizza.awesomepizza.models.Order;
import com.awesomepizza.awesomepizza.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return salvaOrdine(order);
    }

    @PutMapping("/aggiornaOrdine")
    public Order updateOrderStatus(@RequestParam Long id) throws Throwable {
        Order existingOrder = orderRepository.findById(id).orElseThrow(new OrderNotFoundException("Ordine non trovato"));
        com.awesomepizza.awesomepizza.models.StatusEnum status = existingOrder.getStatus();
        existingOrder.setStatus(status.nextStatus());
        return salvaOrdine(existingOrder);
    }

    private Order salvaOrdine(Order existingOrder) {
        return orderRepository.save(existingOrder);
    }

    @DeleteMapping("/deleteOrder")
    public void deleteOrder(@RequestParam Long id) throws Throwable {
        Order existingOrder = orderRepository.findById(id).orElseThrow(new OrderNotFoundException("Ordine non trovato"));
        orderRepository.delete(existingOrder);
    }


}
