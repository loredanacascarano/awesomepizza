package com.awesomepizza.awesomepizza.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pizzas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ElementCollection
    private List<String> ingredients;

    @Column(nullable = false)
    private double cost;
}
