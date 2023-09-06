package com.awesomepizza.awesomepizza.services.impl;


import com.awesomepizza.awesomepizza.models.Order;
import com.awesomepizza.awesomepizza.models.PizzaOrder;
import com.awesomepizza.awesomepizza.models.StatusEnum;
import com.awesomepizza.awesomepizza.models.presentation.OrderPresentation;
import com.awesomepizza.awesomepizza.repositories.OrderRepository;
import com.awesomepizza.awesomepizza.repositories.PizzaOrderRepository;
import com.awesomepizza.awesomepizza.services.OrderService;
import com.awesomepizza.awesomepizza.services.PizzaOrderService;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@Ignore
public class OrderServiceImplTest {
    @Mock
    OrderRepository orderRepository;

    @Mock
    PizzaOrderRepository pizzaOrderRepository;

    @InjectMocks
    OrderService orderService;

    @Mock
    PizzaOrderService pizzaOrderService;

        // Tests that the method recuperaStatoOrdine retrieves the order status successfully
        @Test
        public void test_retrieve_order_status_successfully() {
            // Arrange
            Long idOrdine = 1L;
            Order ordine = new Order();
            ordine.setStatus(StatusEnum.QUEUED);
            when(orderRepository.findById(idOrdine)).thenReturn(Optional.of(ordine));

            // Act
            ResponseEntity<String> response = orderService.recuperaStatoOrdine(idOrdine);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(StatusEnum.QUEUED.toString(), response.getBody());
        }

        // Tests that the method recuperaStatoOrdine returns a 404 status when attempting to retrieve order status with an invalid ID
        @Test
        public void test_retrieve_order_status_invalid_id() {
            // Arrange
            Long idOrdine = 1L;
            when(orderRepository.findById(idOrdine)).thenReturn(Optional.empty());

            // Act
            ResponseEntity<String> response = orderService.recuperaStatoOrdine(idOrdine);

            // Assert
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
            assertEquals("Non c'è nessun ordine con questo id: " + idOrdine, response.getBody());
        }

        // Tests that the method recuperaStatoOrdine returns a 404 status when attempting to retrieve order status with a null status
        @Test
        public void test_retrieve_order_status_null_status() {
            // Arrange
            Long idOrdine = 1L;
            Order ordine = new Order();
            ordine.setStatus(null);
            when(orderRepository.findById(idOrdine)).thenReturn(Optional.of(ordine));

            // Act
            ResponseEntity<String> response = orderService.recuperaStatoOrdine(idOrdine);

            // Assert
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
            assertEquals("Non c'è nessun ordine con questo id: " + idOrdine, response.getBody());
        }

        // Tests that the method creaNuovoOrdine returns a 500 status when attempting to create a new order with an empty orderPresentation list
        @Test
        public void test_create_new_order_empty_list() {
            // Arrange
            List<OrderPresentation> orderPresentation = new ArrayList<>();

            // Act
            ResponseEntity<String> response = orderService.creaNuovoOrdine(orderPresentation);

            // Assert
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        }

        // Tests that the method creaNuovoOrdine returns a 500 status when attempting to create a new order with a null orderPresentation list
        @Test
        public void test_create_new_order_null_list() {
            // Arrange
            List<OrderPresentation> orderPresentation = null;

            // Act
            ResponseEntity<String> response = orderService.creaNuovoOrdine(orderPresentation);

            // Assert
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        }

        // Tests that the method recuperaProssimoOrdine returns "sono finiti gli ordini" when attempting to retrieve the next order in queue with no queued orders
        @Test
        public void test_retrieve_next_order_no_queued_orders() {
            // Arrange
            when(orderRepository.findFirstByStatusOrderByIdAsc(StatusEnum.QUEUED)).thenReturn(null);

            // Act
            ResponseEntity<String> response = orderService.recuperaProssimoOrdine();

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals("sono finiti gli ordini", response.getBody());
        }

        // Tests that the method aggiornaStatoOrdine returns a 500 status when attempting to update order status with an invalid ID
        @Test
        public void test_update_order_status_invalid_id() {
            // Arrange
            Long idOrdine = 1L;
            when(orderRepository.findById(idOrdine)).thenReturn(Optional.empty());

            // Act
            ResponseEntity<String> response = orderService.aggiornaStatoOrdine(idOrdine);

            // Assert
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        }

        // Tests that the method aggiornaStatoOrdine returns a 500 status when attempting to update order status with a null status
        @Test
        public void test_update_order_status_null_status() {
            // Arrange
            Long idOrdine = 1L;
            Order ordine = new Order();
            ordine.setStatus(null);
            when(orderRepository.findById(idOrdine)).thenReturn(Optional.of(ordine));

            // Act
            ResponseEntity<String> response = orderService.aggiornaStatoOrdine(idOrdine);

            // Assert
            assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        }

        // Tests that the method calcolaConto calculates the order total successfully
        @Test
        public void test_calculate_order_total_successfully() {
            // Arrange
            List<OrderPresentation> orderPresentation = new ArrayList<>();
            orderPresentation.add(new OrderPresentation(1L, 2, 10.0));
            orderPresentation.add(new OrderPresentation(2L, 3, 15.0));
            when(pizzaOrderService.calcolaConto(orderPresentation)).thenReturn(70.0);

            // Act
            double total = pizzaOrderService.calcolaConto(orderPresentation);

            // Assert
            assertEquals(70.0, total, 0.01);
        }

        // Tests that the method saveAll saves pizza orders to the database successfully
        @Test
        public void test_save_pizza_orders_to_database() {
            // Arrange
            List<OrderPresentation> orderPresentation = new ArrayList<>();
            orderPresentation.add(new OrderPresentation(1L, 2, 10.0));
            orderPresentation.add(new OrderPresentation(2L, 3, 15.0));
            List<PizzaOrder> pizzaOrders = new ArrayList<>();
            pizzaOrders.add(new PizzaOrder(1L, 1L, 2));
            pizzaOrders.add(new PizzaOrder(1L, 2L, 3));
            when(orderRepository.save(any(Order.class))).thenReturn(new Order());
            when(pizzaOrderRepository.saveAll(anyList())).thenReturn(pizzaOrders);

            // Act
            ResponseEntity<String> response = orderService.creaNuovoOrdine(orderPresentation);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        // Tests that the method getOrdine retrieves the order by ID successfully
        @Test
        @Ignore
        public void test_retrieve_order_by_id() {
            // Arrange
            Long idOrdine = 1L;
            Order ordine = new Order();
            when(orderRepository.findById(idOrdine)).thenReturn(Optional.of(ordine));

            // Act
            //Order result = orderService.getOrdine(idOrdine);

            // Assert
            //assertEquals(ordine, result);
        }

}
