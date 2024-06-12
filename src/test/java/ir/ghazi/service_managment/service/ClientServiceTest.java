package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.model.Client;
import ir.ghazi.service_managment.repository.ClientRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
                .email("mohammadreza@gmail.com")
                .password("Aa@12345")
                .registerDate(LocalDate.now())
                .build();
        Client clientSaved = clientService.saveClient(client);

        Optional<Client> byEmail = clientRepository.findByEmail(clientSaved.getEmail());
        assertNotNull(byEmail);
        assertEquals("mohammadreza", byEmail.get().getFirstName());
        assertEquals("ghazi", byEmail.get().getLastName());
        assertEquals("mohammadreza@gmail.com", byEmail.get().getEmail());
        assertEquals("Aa@12345", byEmail.get().getPassword());
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

        Optional<Client> byEmail = clientRepository.findByEmail(client.getEmail());

        assertFalse(byEmail.isPresent());
    }

    @Test
    @DisplayName("Check Validation last name")
    void saveClientValidationLastName() {
        Client client = Client.builder()
                .firstName("mohammad")
                .lastName("1234")
                .email("123@gmail.com")
                .password("Aa@12345")
                .build();
        clientService.saveClient(client);

        Optional<Client> byEmail = clientRepository.findByEmail(client.getEmail());

        assertFalse(byEmail.isPresent());
    }

    @Test
    @DisplayName("Check Validation email")
    void saveClientValidationEmail() {
        Client client = Client.builder()
                .firstName("saba")
                .lastName("salmanzade")
                .email("Testemail")
                .password("Aa@12345")
                .build();
        clientService.saveClient(client);

        Optional<Client> byEmail = clientRepository.findByEmail(client.getEmail());

        assertFalse(byEmail.isPresent());
    }

    @Test
    @DisplayName("Check Duplicate email")
    void saveClientDuplicateEmail() {
        Client client = Client.builder()
                .firstName("saba")
                .lastName("salmanzade")
                .email("mohammadreza@gmail.com")
                .password("Aa@12345")
                .build();
        clientService.saveClient(client);

        Optional<Client> byEmail = clientRepository.findByEmail(client.getEmail());

        assertTrue(byEmail.isPresent());
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

        Optional<Client> byEmail = clientRepository.findByEmail(client.getEmail());

        assertFalse(byEmail.isPresent());
    }

    @Test
    @DisplayName("Check for client sign in")
    void clientSignIn() {
        Optional<Client> client = clientService.clientSignIn("mohammadreza@gmail.com", "Aa@12345");
        assertTrue(client.isPresent());
    }

    @Test
    @DisplayName("Check for client not sign in")
    void clientSignInError() {
        Optional<Client> client = clientService.clientSignIn("notsign@gmail.com", "Aa@12345");
        assertFalse(client.isPresent());
    }

    @Test
    @DisplayName("Check for update password")
    void updatePassword(){
        Optional<Client> byEmail = clientRepository.findByEmail("mohammadreza@gmail.com");
        Client client = byEmail.get();
        client.setPassword("Mm@12345");
        clientService.updateClient(client);

        assertEquals(client.getPassword(),"Mm@12345" );
    }
    @Test
    @DisplayName("Check for dont update password")
    void dontUpdatePassword(){
        Optional<Client> byEmail = clientRepository.findByEmail("mohammadreza@gmail.com");
        Client client = byEmail.get();
        client.setPassword("123aaa");
        clientService.updateClient(client);

        assertNotEquals(client.getPassword(),"Mm@12345");
    }
}