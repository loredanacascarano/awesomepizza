package com.awesomepizza.awesomepizza.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@Table(name = "pizza_order")
@NoArgsConstructor
public class PizzaOrder {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "pizza_id")
    private Long pizzaId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToMany
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false, updatable = false)
    private List<Order> order;

    @ManyToMany
    @JoinColumn(name = "pizza_id", referencedColumnName = "id", nullable = false, updatable = false)
    private List<Pizza> pizza;

    public PizzaOrder(Long orderId, Long pizzaId, Integer quantity) {
        this.orderId = orderId;
        this.pizzaId = pizzaId;
        this.quantity = quantity;
    }

}