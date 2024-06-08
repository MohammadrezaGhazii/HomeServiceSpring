package ir.ghazi.service_managment.repository;

import ir.ghazi.service_managment.model.Services;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Services,Long> {
}
