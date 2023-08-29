package com.awesomepizza.awesomepizza.repositories;

import com.awesomepizza.awesomepizza.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
