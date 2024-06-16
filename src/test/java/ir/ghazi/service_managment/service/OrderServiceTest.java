package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.enums.OrderSituation;
import ir.ghazi.service_managment.model.Client;
import ir.ghazi.service_managment.model.Order;
import ir.ghazi.service_managment.model.SubService;
import ir.ghazi.service_managment.repository.ClientRepository;
import ir.ghazi.service_managment.repository.OrderRepository;
import ir.ghazi.service_managment.repository.SubServiceRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private SubServiceRepository subServiceRepository;

    @Test
    void addOrder() {
        Optional<Client> byEmailClient = clientRepository.findByEmail("mohammadreza@gmail.com");
        Client client = byEmailClient.get();
        Optional<SubService> byName = subServiceRepository.findByName("Piping");
        SubService subService = byName.get();

        Order order = Order.builder()
                .address("Namjoo")
                .explain("Nothing")
                .orderDate(LocalDate.now())
                .orderSituation(OrderSituation.WAIT_FOR_SPECIALIST_OFFER)
                .requestedDate(LocalDate.of(2024,8,22))
                .requestedTime(LocalTime.of(20,50))
                .client(client)
                .subService(subService)
                .build();
        orderService.addOrder(order);

        Optional<Order> byId = orderRepository.findById(1L);
        assertTrue(byId.isPresent());
    }

    @Test
    @DisplayName("Check time for change situation to STARTED")
    void checkTimeForStartOrder(){
        orderService.addStartWorkFromClient(1L);
        Optional<Order> byId = orderRepository.findById(1L);
        Order order = byId.get();

        assertEquals(order.getOrderSituation() , OrderSituation.STARTED);
    }
}