package ir.ghazi.service_managment.repository;

import ir.ghazi.service_managment.model.Offer;
import ir.ghazi.service_managment.model.Order;
import ir.ghazi.service_managment.model.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findByOrder(Order order);

    List<Offer> findBySpecialist(Specialist specialist);
}
