//package ir.ghazi.service_managment.service;
//
//import ir.ghazi.service_managment.model.Admin;
//import ir.ghazi.service_managment.repository.AdminRepository;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class AdminServiceTest {
//    @Autowired
//    private AdminRepository adminRepository;
//
//    @Autowired
//    private AdminService adminService;
//
//    @DisplayName("Check Save Ok")
//    @Order(1)
//    @Test
//    public void setAdmin() {
//        Admin admin = Admin.builder()
//                .firstName("mohammad")
//                .lastName("ghazi")
//                .email("mohammad@gmail.com")
//                .password("Aa@12345")
//                .build();
//        adminRepository.save(admin);
//
//        Optional<Admin> byEmail = adminRepository.findByEmail(admin.getEmail());
//        assertTrue(byEmail.isPresent());
//    }
//
//    @Test
//    @DisplayName("Check for admin sign in")
//    void signInAdmin() {
//        Optional<Admin> adminFind = adminService.signInAdmin("mohammad@gmail.com", "Aa@12345");
//        assertTrue(adminFind.isPresent());
//    }
//
//    @Test
//    @DisplayName("Check for client not sign in")
//    void signInAdminFalse() {
//        Optional<Admin> adminFind = adminService.signInAdmin("ali@gmail.com", "Aa@12345");
//        assertFalse(adminFind.isPresent());
//    }
//}