package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.base.exception.NotFoundException;
import ir.ghazi.service_managment.base.exception.ValidationException;
import ir.ghazi.service_managment.enums.Role;
import ir.ghazi.service_managment.model.Client;
import ir.ghazi.service_managment.model.CreditClient;
import ir.ghazi.service_managment.repository.ClientRepository;
import ir.ghazi.service_managment.utilities.Validation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    private final CreditClientService creditClientService;

    private final EntityManager entityManager;

    private final BCryptPasswordEncoder passwordEncoder;


    public Client saveClient(Client client) {
        if (!Validation.isNameValid(client.getFirstName())) {
            throw new ValidationException("Firstname should just in letters");
        } else if (!Validation.isNameValid(client.getLastName())) {
            throw new ValidationException("Lastname should just in letters");
        } else if (!Validation.isEmailValid(client.getEmail())) {
            throw new ValidationException("Email is not valid ! || Enter like this : johnsons@gmail.com");
        } else if (!Validation.isPasswordValid(client.getPassword())) {
            throw new ValidationException("password is not strong ! || Enter like : Aa@12345");
        } else if (clientRepository.findByEmail(client.getEmail()).isPresent()) {
            log.warn("This " + client.getEmail() + " is already registered !!!");
        } else {
            client.setRegisterDate(LocalDate.now());
            client.setRole(Role.ROLE_CLIENT);
            client.setPassword(passwordEncoder.encode(client.getPassword()));
            clientRepository.save(client);
            CreditClient creditClient = CreditClient.builder()
                    .client(client)
                    .build();
            creditClientService.addCredit(creditClient);
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

    public Client findByEmail(String email) {
        Optional<Client> byEmail = clientRepository.findByEmail(email);
        Client client = null;
        if (byEmail.isPresent())
            client = byEmail.get();
        else
            throw new ValidationException("Email is not available");
        return client;
    }

    public Optional<Client> emailOptional(String email){
        return clientRepository.findByEmail(email);
    }

    public List<Client> filterClient(String column , String value){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Client> query = criteriaBuilder.createQuery(Client.class);
        Root<Client> clientRoot = query.from(Client.class);

        Predicate columnFilter = criteriaBuilder.equal(clientRoot.get(column), value);
        query.where(criteriaBuilder.and(columnFilter));

        return entityManager.createQuery(query).getResultList();
    }

    public LocalDate registerDate(String email){
        Optional<Client> client = clientRepository.findByEmail(email);
        LocalDate registerDate = null;
        if (client.isPresent()){
            Client client1 = client.get();
            registerDate = client1.getRegisterDate();
        }
        return registerDate;
    }
}
