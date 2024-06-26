package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.base.exception.ValidationException;
import ir.ghazi.service_managment.model.Rate;
import ir.ghazi.service_managment.repository.RateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RateService {

    private final RateRepository rateRepository;

    public Rate saveRate(Rate rate) {
        if (rate.getScore() > 5)
            throw new ValidationException("Number is upper than 5 or lower than 0 , it should between 0 - 5");
        return rateRepository.save(rate);
    }
}
