package ir.ghazi.service_managment.repository;

import ir.ghazi.service_managment.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Services, Long> {
    Optional<Services> findByName(String name);
}
