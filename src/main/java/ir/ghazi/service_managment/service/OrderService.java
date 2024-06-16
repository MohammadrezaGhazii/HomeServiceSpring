package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.model.FieldSpecialist;
import ir.ghazi.service_managment.model.Order;
import ir.ghazi.service_managment.model.Specialist;
import ir.ghazi.service_managment.model.SubService;
import ir.ghazi.service_managment.repository.FieldSpecialistRepository;
import ir.ghazi.service_managment.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
