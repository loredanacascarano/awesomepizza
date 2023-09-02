package com.awesomepizza.awesomepizza.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pizza_orders")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class PizzaOrder {

    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "order_id", nullable = false, insertable=false, updatable=false)
    private Long orderId;

    @Column(name = "pizza_id", nullable = false, insertable=false, updatable=false)
    private Long pizzaId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToMany
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false, updatable = false)
    private List<Order> order;

    @ManyToMany
    @JoinColumn(name = "pizza_id", referencedColumnName = "id", nullable = false, updatable = false)
    private List<Pizza> pizza;
}