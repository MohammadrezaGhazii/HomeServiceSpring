package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.model.Client;
import ir.ghazi.service_managment.repository.ClientRepository;
import ir.ghazi.service_managment.utilities.Validation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client saveClient(Client client) {
        if (!Validation.isNameValid(client.getFirstName()) &&
                Validation.isNameValid(client.getLastName())){
            System.out.println("Firstname and last name should be words only !!!");
        }
        else if (!Validation.isEmailValid(client.getEmail())){
            System.out.println("Email is not valid ! || Enter like this : johnsons@gmail.com");
        }
        else if (!Validation.isPasswordValid(client.getPassword())){
            System.out.println("password is not strong ! || Enter like : Aa@12345");
        }
        else if (clientRepository.findByEmail(client.getEmail()).isPresent()){
            System.out.println("This " + client.getEmail() + " is already registered !!!");
            System.out.println("Try another email");
        }
        else
            clientRepository.save(client);
        return client;
    }
}
