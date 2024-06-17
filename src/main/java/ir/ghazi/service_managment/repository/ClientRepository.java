package ir.ghazi.service_managment.repository;

import ir.ghazi.service_managment.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);

    Optional<Client> findByEmailAndPassword(String email, String password);
}
