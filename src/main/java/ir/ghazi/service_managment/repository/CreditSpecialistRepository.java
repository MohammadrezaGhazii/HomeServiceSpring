package ir.ghazi.service_managment.repository;

import ir.ghazi.service_managment.model.CreditSpecialist;
import ir.ghazi.service_managment.model.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreditSpecialistRepository extends JpaRepository<CreditSpecialist, Long> {
    Optional<CreditSpecialist> findBySpecialist (Specialist specialist);
}
