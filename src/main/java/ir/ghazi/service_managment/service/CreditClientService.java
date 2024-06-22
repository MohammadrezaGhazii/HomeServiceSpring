package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.model.CreditClient;
import ir.ghazi.service_managment.repository.CreditClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditClientService {

    private final CreditClientRepository clientRepository;
    public void addCredit(CreditClient creditClient){
        clientRepository.save(creditClient);
    }
}
