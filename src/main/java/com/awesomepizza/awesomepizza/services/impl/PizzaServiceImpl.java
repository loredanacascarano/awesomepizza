package com.awesomepizza.awesomepizza.services.impl;

import com.awesomepizza.awesomepizza.models.Pizza;
import com.awesomepizza.awesomepizza.repositories.PizzaRepository;
import com.awesomepizza.awesomepizza.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class PizzaServiceImpl implements PizzaService {
    @Autowired
    PizzaRepository pizzaRepository;

    @Override
    public ResponseEntity<String> recuperaMenu() {
        ResponseEntity<String> response;

        List<Pizza> elencoPizze = pizzaRepository.findAll();

        response = CollectionUtils.isEmpty(elencoPizze)
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body("Non ci sono pizze da visualizzare")
                : ResponseEntity.ok(elencoPizze.toString());
        return response;
    }
}
