package ir.ghazi.service_managment.repository;

import ir.ghazi.service_managment.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
}
