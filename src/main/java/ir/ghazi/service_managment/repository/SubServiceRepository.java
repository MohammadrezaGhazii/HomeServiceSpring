package ir.ghazi.service_managment.repository;

import ir.ghazi.service_managment.model.SubService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubServiceRepository extends JpaRepository<SubService, Long> {
    Optional<SubService> findByName(String name);
}
