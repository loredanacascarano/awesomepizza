package com.awesomepizza.awesomepizza.services;

import com.awesomepizza.awesomepizza.models.presentation.OrderPresentation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderService {

    ResponseEntity<String> recuperaStatoOrdine(Long idOrdine);

    ResponseEntity<String> creaNuovoOrdine(List<OrderPresentation> orderPresentation);

    ResponseEntity<String> recuperaProssimoOrdine();

    ResponseEntity<String> aggiornaStatoOrdine(Long idOrdine);

    //TODO: AGGIUNGERE METODO PER RECUPERARE UN SINGOLO ORDINE
}
