package ir.ghazi.service_managment;

import ir.ghazi.service_managment.model.Admin;
import ir.ghazi.service_managment.service.AdminService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MainTest {
    private final AdminService adminService;

    public MainTest(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostConstruct
    public void runTest() {
//        adminSignIn();
    }

    private void adminSignIn() {
        String email = "1111";
        String password = "1111";
        Optional<Admin> admin = adminService.signInAdmin(email, password);
        Admin admin1 = admin.get();
        System.out.println(admin1.getFirstName());
    }
}
