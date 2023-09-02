package com.awesomepizza.awesomepizza.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "pizzas")
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cost", nullable = false)
    private Double cost;

    @Column(name = "ingredients",  columnDefinition = "JSON")
    private String ingredients;

}