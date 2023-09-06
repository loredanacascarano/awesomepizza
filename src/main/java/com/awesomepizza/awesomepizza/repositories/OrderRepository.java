package com.awesomepizza.awesomepizza.repositories;

import com.awesomepizza.awesomepizza.models.Order;
import com.awesomepizza.awesomepizza.models.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findFirstByStatusOrderByIdAsc(StatusEnum statusEnum);
}
