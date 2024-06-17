package ir.ghazi.service_managment.repository;

import ir.ghazi.service_managment.model.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpecialistRepository extends JpaRepository<Specialist, Long> {
    Optional<Specialist> findByEmail(String email);

    Optional<Specialist> findByEmailAndPassword(String email, String password);
}
