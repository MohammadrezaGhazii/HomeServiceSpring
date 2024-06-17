package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.model.FieldSpecialist;
import ir.ghazi.service_managment.model.Specialist;
import ir.ghazi.service_managment.model.SubService;
import ir.ghazi.service_managment.repository.FieldSpecialistRepository;
import ir.ghazi.service_managment.repository.SpecialistRepository;
import ir.ghazi.service_managment.repository.SubServiceRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FieldSpecialistServiceTest {

    @Autowired
    private FieldSpecialistService fieldSpecialistService;
    @Autowired
    private FieldSpecialistRepository fieldSpecialistRepository;

    @Autowired
    private SpecialistRepository specialistRepository;
    @Autowired
    private SubServiceRepository subServiceRepository;

    @Test
    @Order(1)
    @DisplayName("add field to specialist")
    void addFieldSpecialist() {
        Optional<Specialist> byEmailSpecialist = specialistRepository.findByEmail("mohammadreza@gmail.com");
        Specialist specialist = byEmailSpecialist.get();
        Optional<SubService> byNameSubService = subServiceRepository.findByName("Piping");
        SubService subService = byNameSubService.get();
        Optional<SubService> byNameSubService1 = subServiceRepository.findByName("phone repairs");
        SubService subService1 = byNameSubService1.get();

        FieldSpecialist fieldSpecialist = FieldSpecialist.builder()
                .specialist(specialist)
                .subService(subService)
                .build();

        fieldSpecialistService.addFieldSpecialist(fieldSpecialist);
        Optional<FieldSpecialist> byId = fieldSpecialistRepository.findById(1L);

        FieldSpecialist fieldSpecialist1 = FieldSpecialist.builder()
                .specialist(specialist)
                .subService(subService1)
                .build();

        fieldSpecialistService.addFieldSpecialist(fieldSpecialist1);
        Optional<FieldSpecialist> byId1 = fieldSpecialistRepository.findById(2L);

        assertTrue(byId.isPresent());
        assertTrue(byId1.isPresent());
    }
}