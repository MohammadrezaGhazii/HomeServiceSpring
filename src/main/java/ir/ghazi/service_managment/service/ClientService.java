package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.base.exception.NotFoundException;
import ir.ghazi.service_managment.model.Client;
import ir.ghazi.service_managment.repository.ClientRepository;
import ir.ghazi.service_managment.utilities.Validation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client saveClient(Client client) {
        if (!Validation.isNameValid(client.getFirstName())) {
            log.warn("Firstname should just in letters");
        } else if (!Validation.isNameValid(client.getLastName())) {
            log.warn("Lastname should just in letters");
        } else if (!Validation.isEmailValid(client.getEmail())) {
            log.warn("Email is not valid ! || Enter like this : johnsons@gmail.com");
        } else if (!Validation.isPasswordValid(client.getPassword())) {
            log.warn("password is not strong ! || Enter like : Aa@12345");
        } else if (clientRepository.findByEmail(client.getEmail()).isPresent()) {
            log.warn("This " + client.getEmail() + " is already registered !!!");
        } else {
            clientRepository.save(client);
            log.info(client.getEmail() + " has successfully add !");
        }
        return client;
    }

    public Optional<Client> clientSignIn(String email, String password) {
        try {
            Optional<Client> find = clientRepository.findByEmailAndPassword(email, password);
            find.orElseThrow(() -> new NotFoundException("Entity not found"));
            log.info("Welcome " + find.get().getFirstName());
            return find;
        } catch (Exception e) {
            log.error("An error in Client sign in ");
            return Optional.empty();
        }
    }

    public void updateClient(Client client) {
        if (!Validation.isPasswordValid(client.getPassword())) {
            log.warn("password is not strong ! || Enter like : Aa@12345");
        } else
            clientRepository.save(client);
    }
}
