package com.awesomepizza.awesomepizza.services.impl;

import com.awesomepizza.awesomepizza.models.presentation.OrderPresentation;
import com.awesomepizza.awesomepizza.services.PizzaOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaOrderServiceImpl implements PizzaOrderService {


    @Override
    public double calcolaConto(List<OrderPresentation> ordersPresentation) {
        double prezzoTotale = 0;
        for (OrderPresentation ordine : ordersPresentation) {
            prezzoTotale += ordine.getAmount() * ordine.getNumeroPizzeOrdinate();
        }
        return prezzoTotale;
    }
}
