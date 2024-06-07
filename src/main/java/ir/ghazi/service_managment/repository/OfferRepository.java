package ir.ghazi.service_managment.repository;

import ir.ghazi.service_managment.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer,Long> {
}
