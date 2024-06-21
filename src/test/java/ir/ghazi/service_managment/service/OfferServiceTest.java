//package ir.ghazi.service_managment.service;
//
//import ir.ghazi.service_managment.enums.OfferSituation;
//import ir.ghazi.service_managment.enums.OrderSituation;
//import ir.ghazi.service_managment.model.Offer;
//import ir.ghazi.service_managment.model.Order;
//import ir.ghazi.service_managment.model.Specialist;
//import ir.ghazi.service_managment.repository.OfferRepository;
//import ir.ghazi.service_managment.repository.OrderRepository;
//import ir.ghazi.service_managment.repository.SpecialistRepository;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class OfferServiceTest {
//
//    @Autowired
//    private OfferService offerService;
//    @Autowired
//    private OfferRepository offerRepository;
//
//    @Autowired
//    private OrderRepository orderRepository;
//    @Autowired
//    private SpecialistRepository specialistRepository;
//
//    @DisplayName("Check Save Ok")
//    @org.junit.jupiter.api.Order(1)
//    @Test
//    void saveOffer() {
//        Order order = orderRepository.findById(1L).get();
//        Specialist specialist = specialistRepository.findByEmail("mohammadreza@gmail.com").get();
//        Offer offer = Offer.builder()
//                .offerPrice(26000D)
//                .requestedDate(LocalDate.of(2024, 7, 16))
//                .requestedTime(LocalTime.of(20, 50))
//                .timeTodo(120D)
//                .offerSituation(OfferSituation.WAITING)
//                .order(order)
//                .specialist(specialist)
//                .build();
//        offerService.saveOffer(offer, order, specialist);
//
//        Optional<Offer> byId = offerRepository.findById(1L);
//        assertTrue(byId.isPresent());
//    }
//
//    @DisplayName("Check Save With Low Price")
//    @Test
//    void saveOfferWithLowPrice() {
//        Order order = orderRepository.findById(1L).get();
//        Specialist specialist = specialistRepository.findByEmail("mohammadreza@gmail.com").get();
//        Offer offer = Offer.builder()
//                .offerPrice(20000D)
//                .requestedDate(LocalDate.of(2024, 7, 16))
//                .requestedTime(LocalTime.of(20, 50))
//                .timeTodo(120D)
//                .order(order)
//                .specialist(specialist)
//                .build();
//        offerService.saveOffer(offer, order, specialist);
//
//        Optional<Offer> byId = offerRepository.findById(2L);
//        assertFalse(byId.isPresent());
//    }
//
//    @DisplayName("Check Save With Specialist without related field")
//    @Test
//    void saveOfferWithoutRelatedField() {
//        Order order = orderRepository.findById(1L).get();
//        Specialist specialist = specialistRepository.findByEmail("ali@gmail.com").get();
//        Offer offer = Offer.builder()
//                .offerPrice(20000D)
//                .requestedDate(LocalDate.of(2024, 7, 16))
//                .requestedTime(LocalTime.of(20, 50))
//                .timeTodo(120D)
//                .order(order)
//                .specialist(specialist)
//                .build();
//        offerService.saveOffer(offer, order, specialist);
//
//        Optional<Offer> byId = offerRepository.findById(2L);
//        assertFalse(byId.isPresent());
//    }
//
//    @DisplayName("Check Save When Order is Done")
//    @Test
//    void saveOfferWhenOrderIsDone() {
//        Order order = orderRepository.findById(2L).get();
//        Specialist specialist = specialistRepository.findByEmail("mohammadreza@gmail.com").get();
//        Offer offer = Offer.builder()
//                .offerPrice(20000D)
//                .requestedDate(LocalDate.of(2024, 7, 16))
//                .requestedTime(LocalTime.of(20, 50))
//                .timeTodo(120D)
//                .order(order)
//                .specialist(specialist)
//                .build();
//        offerService.saveOffer(offer, order, specialist);
//
//        Optional<Offer> byId = offerRepository.findById(2L);
//        assertFalse(byId.isPresent());
//    }
//
//    @DisplayName("List offers by sort offer price")
//    @Test
//    void sortOfferPrice() {
//        List<Double> doubles = offerService.listByOfferPrice(1L);
//
//        assertEquals(doubles.size(), 3);
//    }
//
//    @DisplayName("List offers by sort offer specialist scores")
//    @Test
//    void sortOfferSpecialistScores() {
//        List<Double> doubles = offerService.listByScoreSpecialist(1L);
//
//        assertEquals(doubles.size(), 3);
//    }
//
//    @DisplayName("Choose offer from client")
//    @Test
//    void chooseOfferFromClient() {
//        Optional<Offer> byIdOffer = offerRepository.findById(1L);
//        Offer offer = byIdOffer.get();
//        offerService.chooseOfferFromClient(offer);
//        Order order = offer.getOrder();
//
//        assertEquals(offer.getOfferSituation(), OfferSituation.ACCEPTED);
//        assertEquals(order.getOrderSituation(), OrderSituation.WAIT_FOR_SPECIALIST_COMING);
//    }
//
//    @DisplayName("Choose offer from client Error")
//    @Test
//    void chooseOfferFromClientError() {
//        Optional<Offer> byIdOffer = offerRepository.findById(1L);
//        Offer offer = byIdOffer.get();
//        offerService.chooseOfferFromClient(offer);
//
//        assertEquals(offer.getOfferSituation(), OfferSituation.ACCEPTED);
//    }
//}