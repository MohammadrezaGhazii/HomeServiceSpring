package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.enums.OrderSituation;
import ir.ghazi.service_managment.model.*;
import ir.ghazi.service_managment.repository.FieldSpecialistRepository;
import ir.ghazi.service_managment.repository.OfferRepository;
import ir.ghazi.service_managment.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final FieldSpecialistRepository fieldSpecialistRepository;
    private final OrderService orderService;
    private final OrderRepository orderRepository;

    public OfferService(OfferRepository offerRepository, FieldSpecialistRepository fieldSpecialistRepository, OrderService orderService, OrderRepository orderRepository) {
        this.offerRepository = offerRepository;
        this.fieldSpecialistRepository = fieldSpecialistRepository;
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    public void saveOffer(Offer offer, Order order, Specialist specialist) {
        SubService subServiceOrder = order.getSubService();
        orderService.listOrderBySubServiceAndSpecialist(subServiceOrder , specialist);

        Optional<FieldSpecialist> bySpecialistAndSubService =
                fieldSpecialistRepository.findBySpecialistAndSubService(specialist, subServiceOrder);

        if (bySpecialistAndSubService.isEmpty()) {
            log.error("You can't choose this");
        }
        else if (!order.getOrderSituation().equals(OrderSituation.WAIT_FOR_SPECIALIST_OFFER)){
            log.error("This order is done !!!");
        }
        else if (offer.getOfferPrice() < subServiceOrder.getBasePrice()){
            log.error("The price should be up than " + subServiceOrder.getBasePrice());
        }
        else
            offerRepository.save(offer);
    }

    public List<Double> listByOfferPrice(Long id){
        Optional<Order> byId = orderRepository.findById(id);
        Order order = byId.get();

        List<Offer> offersByOrder = offerRepository.findByOrder(order);
        List<Double> offerPrices = new ArrayList<>();
        for (Offer offer : offersByOrder) {
            Double offerPrice = offer.getOfferPrice();
            offerPrices.add(offerPrice);
        }
        Collections.sort(offerPrices);
        for (Double offerPrice : offerPrices) {
            log.info(String.valueOf(offerPrice));
        }
        return offerPrices;
    }

    public List<Double> listByScoreSpecialist(Long id){
        Optional<Order> byId = orderRepository.findById(id);
        Order order = byId.get();

        List<Offer> offersByOrder = offerRepository.findByOrder(order);
        List<Double> offerSpecialistScores = new ArrayList<>();
        for (Offer offer : offersByOrder) {
            Double score = offer.getSpecialist().getScore();
            offerSpecialistScores.add(score);
        }
        Collections.sort(offerSpecialistScores);
        for (Double offerSpecialistScore : offerSpecialistScores) {
            log.info(String.valueOf(offerSpecialistScore));
        }
        return offerSpecialistScores;
    }
}
