package com.awesomepizza.awesomepizza.controllers;

import com.awesomepizza.awesomepizza.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pizzaiolo")
public class MerchantController {
    //recupera primo ordine in queued
    @Autowired
    private OrderService orderService;

    @GetMapping
    ResponseEntity<String> recuperaProssimoOrdine(){
        return orderService.recuperaProssimoOrdine();
    }

    //aggiorna stato ordine

    @PutMapping
    ResponseEntity<String> aggiornaStatoOrdine(@RequestParam Long idOrdine){
        return orderService.aggiornaStatoOrdine(idOrdine);
    }
}
