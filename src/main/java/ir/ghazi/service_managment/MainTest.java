package ir.ghazi.service_managment;

import ir.ghazi.service_managment.model.Admin;
import ir.ghazi.service_managment.model.Client;
import ir.ghazi.service_managment.service.AdminService;
import ir.ghazi.service_managment.service.ClientService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MainTest {
    private final AdminService adminService;
    private final ClientService clientService;

    public MainTest(AdminService adminService, ClientService clientService) {
        this.adminService = adminService;
        this.clientService = clientService;
    }

    @PostConstruct
    public void runTest() {
//        adminSignIn();
//        clientSignUp();
//        clientSignIn();
    }

    private void adminSignIn() {
        String email = "1111";
        String password = "1111";
        Optional<Admin> admin = adminService.signInAdmin(email, password);
        Admin admin1 = admin.get();
        System.out.println(admin1.getFirstName());
    }
    private void clientSignUp() {
        Client client = Client.builder()
                .firstName("mohammadreza")
                .lastName("ghazi")
                .email("mohammadreza@gmail.com")
                .password("Aa@12345")
                .build();
        clientService.saveClient(client);
    }
    private void clientSignIn(){
        clientService.clientSignIn("reza@gmail.com" , "Aa@12345");
    }
}
