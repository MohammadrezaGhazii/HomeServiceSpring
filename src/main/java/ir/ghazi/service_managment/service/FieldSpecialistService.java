package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.base.exception.NotFoundException;
import ir.ghazi.service_managment.model.FieldSpecialist;
import ir.ghazi.service_managment.model.Specialist;
import ir.ghazi.service_managment.model.SubService;
import ir.ghazi.service_managment.repository.FieldSpecialistRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class FieldSpecialistService {
    private final FieldSpecialistRepository fieldSpecialistRepository;

    public FieldSpecialistService(FieldSpecialistRepository fieldSpecialistRepository) {
        this.fieldSpecialistRepository = fieldSpecialistRepository;
    }

    public void addFieldSpecialist(FieldSpecialist fieldSpecialist) {
        fieldSpecialistRepository.save(fieldSpecialist);
    }

    public Optional<FieldSpecialist> findBySpecialistAndSubService(Specialist specialist, SubService subService){
        Optional<FieldSpecialist> bySpecialistAndSubService =
                fieldSpecialistRepository.findBySpecialistAndSubService(specialist, subService);
        if (bySpecialistAndSubService.isEmpty())
            throw new NotFoundException("This specialist " + specialist.getId() +
                    " with this field is not exist");
        else
            return bySpecialistAndSubService;
    }

}
