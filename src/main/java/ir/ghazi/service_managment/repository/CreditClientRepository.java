package ir.ghazi.service_managment.repository;

import ir.ghazi.service_managment.model.Client;
import ir.ghazi.service_managment.model.CreditClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreditClientRepository extends JpaRepository<CreditClient, Long> {
    Optional<CreditClient> findByClient (Client client);
}
