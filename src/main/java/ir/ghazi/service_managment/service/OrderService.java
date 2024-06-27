package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.base.exception.ValidationException;
import ir.ghazi.service_managment.enums.OrderSituation;
import ir.ghazi.service_managment.enums.SpecialistSituation;
import ir.ghazi.service_managment.model.*;
import ir.ghazi.service_managment.repository.FieldSpecialistRepository;
import ir.ghazi.service_managment.repository.OrderRepository;
import ir.ghazi.service_managment.repository.SpecialistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    private final FieldSpecialistRepository fieldSpecialistRepository;

    private final OfferService offerService;

    private final CreditClientService creditClientService;

    private final CreditSpecialistService creditSpecialistService;

    private final SpecialistService specialistService;

    private final SpecialistRepository specialistRepository;

    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    public void listOrderBySubServiceAndSpecialist(SubService subService, Specialist specialist) {
        List<Order> bySubServiceOrder = orderRepository.findBySubService(subService);

        for (Order order : bySubServiceOrder) {
            SubService subService1 = order.getSubService();
            Optional<FieldSpecialist> bySpecialistAndSubService =
                    fieldSpecialistRepository.findBySpecialistAndSubService(specialist, subService1);

            if (bySpecialistAndSubService.isPresent()) {
                log.info("There is any data's");
            }
        }
    }

    public void addStartWorkFromClient(Long id) {
        Optional<Order> byId = orderRepository.findById(id);
        Order order = byId.get();
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();

        LocalDate requestedDate = order.getRequestedDate();
        LocalTime requestedTime = order.getRequestedTime();

        if (localDate.isBefore(requestedDate) && localTime.isBefore(requestedTime)) {
            log.error("You can confirm the order to STARTED after " + localDate + " " + localTime);
        } else {
            order.setOrderSituation(OrderSituation.STARTED);
            orderRepository.save(order);
        }
    }

    public void changeSituationOrderToEnd(Long id) {
        Optional<Order> byId = orderRepository.findById(id);
        Order order = byId.get();

        order.setOrderSituation(OrderSituation.DONE);
        orderRepository.save(order);
        checkForWorkTakeTime(order);
    }

    public void checkForWorkTakeTime(Order order){
        LocalTime now = LocalTime.now();

        Offer offer = offerService.findSpecialistFromAcceptedOffer(order);
        Specialist specialist = offer.getSpecialist();
        LocalTime requestedTime = offer.getRequestedTime();
        Double timeTodo = offer.getTimeTodo();

        double hours = timeTodo/60;

        int hour = requestedTime.getHour();

        hour = hour+(int) hours;

        int hour1 = now.getHour();

        if (hour1 > hour){
            specialist.setScore(specialist.getScore()-(hour1-hour));
            specialistRepository.save(specialist);
            disableAccount(specialist);
        }
    }

    public void disableAccount(Specialist specialist){
        if (specialist.getScore() < 0){
            specialist.setSituation(SpecialistSituation.APPROVE_PENDING);
            specialistRepository.save(specialist);
        }
    }

    public void paymentWallet(Long id) {
        Optional<Order> byId = orderRepository.findById(id);
        Order order = byId.get();
        Client client = order.getClient();

        Offer acceptedOffer = offerService.findSpecialistFromAcceptedOffer(order);
        Specialist specialist = acceptedOffer.getSpecialist();
        Double offerPrice = acceptedOffer.getOfferPrice();

        CreditClient creditClient = creditClientService.findByClient(client);
        CreditSpecialist creditSpecialist = creditSpecialistService.findBySpecialist(specialist);

        if (creditClient.getInventory() >= offerPrice && !order.getOrderSituation().equals(OrderSituation.PAYED)) {
            creditClient.setInventory(creditClient.getInventory() - offerPrice);
            creditClientService.addCredit(creditClient);

            creditSpecialist.setInventory(offerPrice * 0.7);
            creditSpecialistService.addCredit(creditSpecialist);

            order.setOrderSituation(OrderSituation.PAYED);
            orderRepository.save(order);
        } else
            throw new ValidationException("Not enough money in wallet or this order is payed before");
    }
}
