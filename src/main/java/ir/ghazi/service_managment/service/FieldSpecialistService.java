package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.model.FieldSpecialist;
import ir.ghazi.service_managment.repository.FieldSpecialistRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class FieldSpecialistService {
    private final FieldSpecialistRepository fieldSpecialistRepository;

    public FieldSpecialistService(FieldSpecialistRepository fieldSpecialistRepository) {
        this.fieldSpecialistRepository = fieldSpecialistRepository;
    }

    public void addFieldSpecialist(FieldSpecialist fieldSpecialist){
        fieldSpecialistRepository.save(fieldSpecialist);
    }

}
