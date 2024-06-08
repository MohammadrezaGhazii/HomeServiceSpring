package ir.ghazi.service_managment.repository;

import ir.ghazi.service_managment.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findByEmailAndPassword(String email , String password);
}
