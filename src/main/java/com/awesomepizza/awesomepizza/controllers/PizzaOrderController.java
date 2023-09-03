package com.awesomepizza.awesomepizza.controllers;

import com.awesomepizza.awesomepizza.models.PizzaOrder;
import com.awesomepizza.awesomepizza.repositories.PizzaOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizza-orders")
public class PizzaOrderController {

    @Autowired
    private PizzaOrderRepository pizzaOrderService;

    @GetMapping
    public List<PizzaOrder> getAllPizzaOrders() {
        return pizzaOrderService.findAll();
    }

    @PostMapping
    public PizzaOrder createPizzaOrder(@RequestBody PizzaOrder pizzaOrder) {
        return pizzaOrderService.save(pizzaOrder);
    }

    @PutMapping
    public PizzaOrder updatePizzaOrder(@RequestParam  Long id) {
        PizzaOrder pizzaOrder = pizzaOrderService.getById(id);
        return pizzaOrderService.save(pizzaOrder);
    }

    @DeleteMapping("/{id}")
    public void deletePizzaOrder(@RequestParam Long id) {
        PizzaOrder pizzaOrder = pizzaOrderService.getById(id);
        pizzaOrderService.delete(pizzaOrder);
    }
}
