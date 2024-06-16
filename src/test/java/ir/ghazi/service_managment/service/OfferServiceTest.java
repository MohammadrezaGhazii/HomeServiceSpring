package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.model.Offer;
import ir.ghazi.service_managment.model.Order;
import ir.ghazi.service_managment.model.Specialist;
import ir.ghazi.service_managment.repository.OfferRepository;
import ir.ghazi.service_managment.repository.OrderRepository;
import ir.ghazi.service_managment.repository.SpecialistRepository;
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
class OfferServiceTest {

    @Autowired
    private OfferService offerService;
    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private SpecialistRepository specialistRepository;

    @DisplayName("Check Save Ok")
    @org.junit.jupiter.api.Order(1)
    @Test
    void saveOffer() {
        Order order = orderRepository.findById(1L).get();
        Specialist specialist = specialistRepository.findByEmail("mohammadreza@gmail.com").get();
        Offer offer = Offer.builder()
                .offerPrice(26000D)
                .requestedDate(LocalDate.of(2024,7,16))
                .requestedTime(LocalTime.of(20,50))
                .timeTodo(120D)
                .order(order)
                .specialist(specialist)
                .build();
        offerService.saveOffer(offer,order,specialist);

        Optional<Offer> byId = offerRepository.findById(1L);
        assertTrue(byId.isPresent());
    }

    @DisplayName("Check Save With Low Price")
    @Test
    void saveOfferWithLowPrice() {
        Order order = orderRepository.findById(1L).get();
        Specialist specialist = specialistRepository.findByEmail("mohammadreza@gmail.com").get();
        Offer offer = Offer.builder()
                .offerPrice(20000D)
                .requestedDate(LocalDate.of(2024,7,16))
                .requestedTime(LocalTime.of(20,50))
                .timeTodo(120D)
                .order(order)
                .specialist(specialist)
                .build();
        offerService.saveOffer(offer,order,specialist);

        Optional<Offer> byId = offerRepository.findById(2L);
        assertFalse(byId.isPresent());
    }

    @DisplayName("Check Save With Specialist without related field")
    @Test
    void saveOfferWithoutRelatedField() {
        Order order = orderRepository.findById(1L).get();
        Specialist specialist = specialistRepository.findByEmail("ali@gmail.com").get();
        Offer offer = Offer.builder()
                .offerPrice(20000D)
                .requestedDate(LocalDate.of(2024,7,16))
                .requestedTime(LocalTime.of(20,50))
                .timeTodo(120D)
                .order(order)
                .specialist(specialist)
                .build();
        offerService.saveOffer(offer,order,specialist);

        Optional<Offer> byId = offerRepository.findById(2L);
        assertFalse(byId.isPresent());
    }

    @DisplayName("Check Save When Order is Done")
    @Test
    void saveOfferWhenOrderIsDone() {
        Order order = orderRepository.findById(2L).get();
        Specialist specialist = specialistRepository.findByEmail("mohammadreza@gmail.com").get();
        Offer offer = Offer.builder()
                .offerPrice(20000D)
                .requestedDate(LocalDate.of(2024,7,16))
                .requestedTime(LocalTime.of(20,50))
                .timeTodo(120D)
                .order(order)
                .specialist(specialist)
                .build();
        offerService.saveOffer(offer,order,specialist);

        Optional<Offer> byId = offerRepository.findById(2L);
        assertFalse(byId.isPresent());
    }
}