package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.enums.OrderSituation;
import ir.ghazi.service_managment.model.*;
import ir.ghazi.service_managment.repository.FieldSpecialistRepository;
import ir.ghazi.service_managment.repository.OfferRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final FieldSpecialistRepository fieldSpecialistRepository;
    private final OrderService orderService;

    public OfferService(OfferRepository offerRepository, FieldSpecialistRepository fieldSpecialistRepository, OrderService orderService) {
        this.offerRepository = offerRepository;
        this.fieldSpecialistRepository = fieldSpecialistRepository;
        this.orderService = orderService;
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
}
