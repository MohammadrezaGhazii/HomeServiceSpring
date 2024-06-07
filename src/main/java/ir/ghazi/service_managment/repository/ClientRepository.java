package ir.ghazi.service_managment.repository;

import ir.ghazi.service_managment.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
