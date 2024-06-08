package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.model.Client;
import ir.ghazi.service_managment.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class ClientServiceTest {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientRepository clientRepository;

    @Test
    @DisplayName("Check Save Ok")
    @Order(1)
    void saveClient() {
        Client client = Client.builder()
                .firstName("mohammadreza")
                .lastName("ghazi")
                .email("ali@gmail.com")
                .password("Aa@12345")
                .build();
        Client clientSaved = clientService.saveClient(client);

        Optional<Client> byEmailByEmail = clientRepository.findByEmail(clientSaved.getEmail());
        assertNotNull(byEmailByEmail);
        assertEquals("mohammadreza", byEmailByEmail.get().getFirstName());
        assertEquals("ghazi", byEmailByEmail.get().getLastName());
        assertEquals("ali@gmail.com", byEmailByEmail.get().getEmail());
        assertEquals("Aa@12345", byEmailByEmail.get().getPassword());
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

    @Test
    @DisplayName("Check for client sign in")
    void clientSignIn(){
        Optional<Client> client = clientService.clientSignIn("mohammadreza@gmail.com", "Aa@12345");
        assertTrue(client.isPresent());
    }

    @Test
    @DisplayName("Check for client not sign in")
    void clientSignInError(){
        Optional<Client> client = clientService.clientSignIn("notsign@gmail.com", "Aa@12345");
        assertFalse(client.isPresent());
    }
}