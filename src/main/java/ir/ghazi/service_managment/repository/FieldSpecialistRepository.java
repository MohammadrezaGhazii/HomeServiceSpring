package ir.ghazi.service_managment.repository;

import ir.ghazi.service_managment.model.FieldSpecialist;
import ir.ghazi.service_managment.model.Specialist;
import ir.ghazi.service_managment.model.SubService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FieldSpecialistRepository extends JpaRepository<FieldSpecialist, Long> {
    Optional<FieldSpecialist> findBySpecialistAndSubService (Specialist specialist , SubService subService);
}
