package ir.ghazi.service_managment.repository;

import ir.ghazi.service_managment.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long> {
}
