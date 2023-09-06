package com.awesomepizza.awesomepizza.controllers;


import com.awesomepizza.awesomepizza.models.presentation.OrderPresentation;
import com.awesomepizza.awesomepizza.services.OrderService;
import com.awesomepizza.awesomepizza.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/cliente")
public class CustomerController {
    @Autowired
    private PizzaService pizzaService;


    @Autowired
    private OrderService orderService;


    //creazione ordine
    @PostMapping
    public ResponseEntity<String> creaNuovoOrdine(@RequestBody List<OrderPresentation> orderPresentation){
        return orderService.creaNuovoOrdine(orderPresentation);
    }

    //visualizza stato ordine
    @GetMapping("/stato-ordine")
    public ResponseEntity<String> retrieveStatoOrdine(@RequestParam Long idOrdine){
        return orderService.recuperaStatoOrdine(idOrdine);
    }

    //elenco pizze
    @GetMapping("/lista-pizze")
    public ResponseEntity<String> retrievePizze(){
        return pizzaService.recuperaMenu();
    }
}
