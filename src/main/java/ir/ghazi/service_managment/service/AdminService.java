package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.base.exception.NotFoundException;
import ir.ghazi.service_managment.model.Admin;
import ir.ghazi.service_managment.repository.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AdminService {
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Optional<Admin> signInAdmin(String email, String password) {
        try {
            Optional<Admin> find = adminRepository.findByEmailAndPassword(email, password);
            find.orElseThrow(() -> new NotFoundException("Entity not found"));
            log.info("Welcome " + find.get().getFirstName() + " " +find.get().getLastName());
            return find;
        } catch (Exception e) {
            log.error("An error in Admin sign in ");
            return Optional.empty();
        }
    }

    public Admin registerAdmin(Admin admin) {
        return adminRepository.save(admin);
    }
}
