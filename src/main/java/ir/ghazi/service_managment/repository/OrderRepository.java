package ir.ghazi.service_managment.repository;

import ir.ghazi.service_managment.model.Client;
import ir.ghazi.service_managment.model.Order;
import ir.ghazi.service_managment.model.SubService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findBySubService(SubService subService);

    List<Order> findByClient (Client client);
}
