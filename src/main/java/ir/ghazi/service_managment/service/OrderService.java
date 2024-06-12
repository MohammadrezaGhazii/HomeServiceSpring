package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.model.Order;
import ir.ghazi.service_managment.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void addOrder(Order order){
        orderRepository.save(order);
    }
}
