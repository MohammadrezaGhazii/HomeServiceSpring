package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.enums.OrderSituation;
import ir.ghazi.service_managment.model.FieldSpecialist;
import ir.ghazi.service_managment.model.Order;
import ir.ghazi.service_managment.model.Specialist;
import ir.ghazi.service_managment.model.SubService;
import ir.ghazi.service_managment.repository.FieldSpecialistRepository;
import ir.ghazi.service_managment.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final FieldSpecialistRepository fieldSpecialistRepository;

    public OrderService(OrderRepository orderRepository, FieldSpecialistRepository fieldSpecialistRepository) {
        this.orderRepository = orderRepository;
        this.fieldSpecialistRepository = fieldSpecialistRepository;
    }

    public void addOrder(Order order) {
        orderRepository.save(order);
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

    public void changeSituationOrderToEnd(Long id){
        Optional<Order> byId = orderRepository.findById(id);
        Order order = byId.get();

        order.setOrderSituation(OrderSituation.DONE);
        orderRepository.save(order);
    }
}
