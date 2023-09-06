package com.awesomepizza.awesomepizza.services;

import com.awesomepizza.awesomepizza.models.PizzaOrder;
import com.awesomepizza.awesomepizza.models.presentation.OrderPresentation;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PizzaOrderService {

    double calcolaConto(List<OrderPresentation> orderPresentation);
}
