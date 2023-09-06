package com.awesomepizza.awesomepizza.repositories;

import com.awesomepizza.awesomepizza.models.PizzaOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaOrderRepository extends JpaRepository<PizzaOrder, Long> {

}
