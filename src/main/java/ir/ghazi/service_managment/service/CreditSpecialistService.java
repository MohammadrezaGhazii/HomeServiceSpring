package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.model.CreditClient;
import ir.ghazi.service_managment.model.CreditSpecialist;
import ir.ghazi.service_managment.repository.CreditClientRepository;
import ir.ghazi.service_managment.repository.CreditSpecialistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditSpecialistService {

    private final CreditSpecialistRepository creditSpecialistRepository;

    public void addCredit(CreditSpecialist creditSpecialist){
        creditSpecialistRepository.save(creditSpecialist);
    }
}
