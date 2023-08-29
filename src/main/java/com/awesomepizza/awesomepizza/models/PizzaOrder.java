package com.awesomepizza.awesomepizza.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "pizza_order")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PizzaOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;

    @Column(nullable = false)
    private int quantity;
}