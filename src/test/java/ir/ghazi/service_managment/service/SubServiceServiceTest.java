package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.model.SubService;
import ir.ghazi.service_managment.repository.ServiceRepository;
import ir.ghazi.service_managment.repository.SubServiceRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SubServiceServiceTest {
    @Autowired
    private SubServiceService subServiceService;

    @Autowired
    private SubServiceRepository subServiceRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Test
    @DisplayName("Check Save Ok")
    @Order(1)
    void savaSubService() {
        SubService subService = SubService.builder()
                .name("Piping")
                .basePrice(25000D)
                .description("check your house piping")
                .service(serviceRepository.findByName("Phone Services").get())
                .build();
        subServiceService.savaSubService(subService);

        Optional<SubService> byName = subServiceRepository.findByName(subService.getName());

        assertTrue(byName.isPresent());
    }

    @Test
    @DisplayName("Check Save with duplicate name")
    void savaDuplicateSubService() {
        SubService subService = SubService.builder()
                .name("Piping")
                .basePrice(25000D)
                .description("check your house piping")
                .service(serviceRepository.findByName("Phone Services").get())
                .build();
        subServiceService.savaSubService(subService);

        Optional<SubService> byId = subServiceRepository.findById(2L);

        assertFalse(byId.isPresent());
    }
}