package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.base.exception.NotFoundException;
import ir.ghazi.service_managment.base.exception.ValidationException;
import ir.ghazi.service_managment.enums.Role;
import ir.ghazi.service_managment.model.Admin;
import ir.ghazi.service_managment.model.Client;
import ir.ghazi.service_managment.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;

    private final BCryptPasswordEncoder passwordEncoder;

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

    public Admin findByEmail(String email) {
        Optional<Admin> byEmail = adminRepository.findByEmail(email);
        Admin admin = null;
        if (byEmail.isPresent())
            admin = byEmail.get();
        else
            throw new ValidationException("Email is not available");
        return admin;
    }

    public Admin registerAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setRegisterDate(LocalDate.now());
        admin.setRole(Role.ROLE_ADMIN);
        return adminRepository.save(admin);
    }
}
