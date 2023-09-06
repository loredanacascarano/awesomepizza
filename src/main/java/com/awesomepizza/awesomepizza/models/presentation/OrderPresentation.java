package com.awesomepizza.awesomepizza.models.presentation;

import com.awesomepizza.awesomepizza.models.PizzaOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderPresentation {
    Long idPizza;
    Integer numeroPizzeOrdinate;
    Double amount;

}
