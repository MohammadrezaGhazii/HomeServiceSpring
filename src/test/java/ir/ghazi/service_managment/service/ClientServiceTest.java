package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.model.Client;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @Test
    @DisplayName("Save as everything good")
    void saveClient() {
        Client client = Client.builder()
                .firstName("reza")
                .lastName("amoii")
                .email("reza@gmail.com")
                .password("Aa@12345")
                .build();
        clientService.saveClient(client);
    }
    @Test
    @DisplayName("Check Validation name")
    void saveClientValidationName() {
        Client client = Client.builder()
                .firstName("123")
                .lastName("ahmadi")
                .email("123@gmail.com")
                .password("Aa@12345")
                .build();
        clientService.saveClient(client);
    }
    @Test
    @DisplayName("Check Validation lastname")
    void saveClientValidationLastName() {
        Client client = Client.builder()
                .firstName("ahmad")
                .lastName("123")
                .email("123@gmail.com")
                .password("Aa@12345")
                .build();
        clientService.saveClient(client);
    }
    @Test
    @DisplayName("Check Validation email")
    void saveClientValidationEmail() {
        Client client = Client.builder()
                .firstName("saba")
                .lastName("salmanzade")
                .email("abcd123")
                .password("Aa@12345")
                .build();
        clientService.saveClient(client);
    }

    @Test
    @DisplayName("Check Validation password")
    void saveClientValidationPassword() {
        Client client = Client.builder()
                .firstName("saba")
                .lastName("salmanzade")
                .email("abcd@gmail.com")
                .password("zxy5")
                .build();
        clientService.saveClient(client);
    }
}