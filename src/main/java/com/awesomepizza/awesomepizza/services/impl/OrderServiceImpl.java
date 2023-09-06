package com.awesomepizza.awesomepizza.services.impl;

import com.awesomepizza.awesomepizza.models.Order;
import com.awesomepizza.awesomepizza.models.Pizza;
import com.awesomepizza.awesomepizza.models.PizzaOrder;
import com.awesomepizza.awesomepizza.models.StatusEnum;
import com.awesomepizza.awesomepizza.models.presentation.OrderPresentation;
import com.awesomepizza.awesomepizza.repositories.OrderRepository;
import com.awesomepizza.awesomepizza.repositories.PizzaOrderRepository;
import com.awesomepizza.awesomepizza.services.OrderService;
import com.awesomepizza.awesomepizza.services.PizzaOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {
    private static final String ID_NOT_FOUND = "Non c'è nessun ordine con questo id: ";
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    PizzaOrderService pizzaOrderService;

    @Autowired
    PizzaOrderRepository pizzaOrderRepository;

    @Override
    public ResponseEntity<String> recuperaStatoOrdine(Long idOrdine) {
        Order ordine = getOrdine(idOrdine);
        return ordine != null && ordine.getStatus() != null ? ResponseEntity.ok(ordine.getStatus().toString()) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(ID_NOT_FOUND + idOrdine);
    }

    @Override
    public ResponseEntity<String> creaNuovoOrdine(List<OrderPresentation> orderPresentation) {
        Order order = new Order();
        order.setImporto(pizzaOrderService.calcolaConto(orderPresentation));
        order.setStatus(StatusEnum.QUEUED);
        order = orderRepository.save(order);
        //inserisci in pizza order
        List<PizzaOrder> pizzaOrders = new ArrayList<>();
        for (OrderPresentation orderPresentation1 : orderPresentation) {
            pizzaOrders.add(new PizzaOrder(order.getId(), orderPresentation1.getIdPizza(), orderPresentation1.getNumeroPizzeOrdinate()));
        }
        pizzaOrderRepository.saveAll(pizzaOrders);
        return ResponseEntity.ok(order.toString());
    }

    @Override
    public ResponseEntity<String> recuperaProssimoOrdine() {
        Order order = orderRepository.findFirstByStatusOrderByIdAsc(StatusEnum.QUEUED);
        if (order == null) {
            return ResponseEntity.ok("sono finiti gli ordini");
        }

        //TODO IMPLEMENTARE ANCHE IL RELATIVO ORDINE COMPLETO, NON SOLO L'ID

        return ResponseEntity.ok(aggiornaOrdine(order.getId()).toString() );
    }

    @Override
    public ResponseEntity<String> aggiornaStatoOrdine(Long idOrdine) {
        Order ordine = aggiornaOrdine(idOrdine);
        return ResponseEntity.ok(ordine.toString());
    }

    private Order aggiornaOrdine(Long idOrdine) {
        Order ordine = getOrdine(idOrdine);
        ordine.setStatus(ordine.getStatus().nextStatus());
        orderRepository.save(ordine);
        return ordine;
    }

    private Order getOrdine(Long idOrdine) {
        return orderRepository.findById(idOrdine).orElse(null);
    }
}
