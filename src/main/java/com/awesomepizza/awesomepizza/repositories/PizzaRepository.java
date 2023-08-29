package com.awesomepizza.awesomepizza.repositories;


import com.awesomepizza.awesomepizza.models.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PizzaRepository extends JpaRepository<Pizza, Long> {
}
