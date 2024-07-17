package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.base.exception.NotFoundException;
import ir.ghazi.service_managment.enums.OfferSituation;
import ir.ghazi.service_managment.enums.OrderSituation;
import ir.ghazi.service_managment.model.*;
import ir.ghazi.service_managment.model.Order;
import ir.ghazi.service_managment.repository.OfferRepository;
import ir.ghazi.service_managment.repository.OrderRepository;
import ir.ghazi.service_managment.repository.SpecialistRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OfferService {

    private final OfferRepository offerRepository;
    private final FieldSpecialistService fieldSpecialistService;
    private final OrderRepository orderRepository;
    private final SpecialistRepository specialistRepository;
    private final EntityManager entityManager;

    public Offer saveOffer(Offer offer, Long order, Long specialist) {
        Optional<Order> byIdOrder = orderRepository.findById(order);
        Optional<Specialist> byIdSpecialist = specialistRepository.findById(specialist);


        if (byIdOrder.isEmpty() && byIdSpecialist.isEmpty()) {
            throw new NotFoundException("order or specialist does not exist");
        }

        Order order1 = byIdOrder.get();
        SubService subService = order1.getSubService();
        Specialist specialist1 = byIdSpecialist.get();

        offer.setOrder(order1);
        offer.setSpecialist(specialist1);


        Optional<FieldSpecialist> bySpecialistAndSubService =
                fieldSpecialistService.findBySpecialistAndSubService(specialist1, subService);

        Offer save = new Offer();

        if (bySpecialistAndSubService.isEmpty()) {
            log.error("You can't choose this");
        } else if (!order1.getOrderSituation().equals(OrderSituation.WAIT_FOR_SPECIALIST_OFFER)) {
            log.error("This order is done !!!");
        } else if (offer.getOfferPrice() < subService.getBasePrice()) {
            log.error("The price should be up than " + subService.getBasePrice());
        } else {
            save = offerRepository.save(offer);
        }
        return save;
    }

    public List<Double> listByOfferPrice(Long id) {
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

    public List<Double> listByScoreSpecialist(Long id) {
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

    public void chooseOfferFromClient(Offer offer) {
        Order order = offer.getOrder();
        if (offer.getOfferSituation().equals(OfferSituation.ACCEPTED)) {
            log.error("This Order has been done");
        } else {
            offer.setOfferSituation(OfferSituation.ACCEPTED);
            offerRepository.save(offer);
            order.setOrderSituation(OrderSituation.WAIT_FOR_SPECIALIST_COMING);
            orderRepository.save(order);
        }
    }

    public Offer findById(Long id) {
        Optional<Offer> byId = offerRepository.findById(id);
        Offer offer;
        if (byId.isEmpty())
            throw new NotFoundException("This id is not available");
        else
            offer = byId.get();

        return offer;
    }

    public Offer findSpecialistFromAcceptedOffer(Order order) {
        Optional<Offer> byOrderAndAndOfferSituation =
                offerRepository.findByOrderAndAndOfferSituation(order, OfferSituation.ACCEPTED);

        Offer offer;
        if (byOrderAndAndOfferSituation.isPresent()) {
            offer = byOrderAndAndOfferSituation.get();
        } else
            throw new NotFoundException("This order with Situation ACCEPTED is not found");

        return offer;
    }

    public List<Offer> filterSpecialistByOfferSituation(String column, Long value,
                                                        String column1, String value1) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Offer> query = criteriaBuilder.createQuery(Offer.class);
        Root<Offer> offerRoot = query.from(Offer.class);

        Join<Offer, Specialist> specialistJoin = offerRoot.join(column);

        Predicate columnFilter = criteriaBuilder.equal(specialistJoin.get("id"), value);
        Predicate columnFilter1 = criteriaBuilder.equal(offerRoot.get(column1), value1);
        query.where(criteriaBuilder.and(columnFilter, columnFilter1));

        return entityManager.createQuery(query).getResultList();
    }
}
