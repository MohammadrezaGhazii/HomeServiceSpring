package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.model.CreditClient;
import ir.ghazi.service_managment.model.CreditSpecialist;
import ir.ghazi.service_managment.model.Specialist;
import ir.ghazi.service_managment.repository.CreditClientRepository;
import ir.ghazi.service_managment.repository.CreditSpecialistRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditSpecialistService {

    private final CreditSpecialistRepository creditSpecialistRepository;

    public void addCredit(CreditSpecialist creditSpecialist){
        creditSpecialistRepository.save(creditSpecialist);
    }

    public CreditSpecialist findBySpecialist(Specialist specialist){
        Optional<CreditSpecialist> bySpecialist = creditSpecialistRepository.findBySpecialist(specialist);
        return bySpecialist.get();
    }
}
