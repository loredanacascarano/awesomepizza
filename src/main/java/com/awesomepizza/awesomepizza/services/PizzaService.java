package com.awesomepizza.awesomepizza.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface PizzaService {
    ResponseEntity<String> recuperaMenu();
}
