package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.model.Admin;
import ir.ghazi.service_managment.repository.AdminRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class AdminServiceTest {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AdminService adminService;

    @BeforeEach
    public void setAdmin() {
        Admin admin = Admin.builder()
                .firstName("mohammad")
                .lastName("ghazi")
                .email("mohammad@gmail.com")
                .password("Aa@12345")
                .build();
        adminRepository.save(admin);
    }

    @Test
    void signInAdmin() {
        Optional<Admin> adminFind = adminService.signInAdmin("mohammad@gmail.com", "Aa@12345");
        assertTrue(adminFind.isPresent());
    }

    @Test
    void signInAdminFalse() {
        Optional<Admin> adminFind = adminService.signInAdmin("ali@gmail.com", "Aa@12345");

        assertFalse(adminFind.isPresent());
    }
}