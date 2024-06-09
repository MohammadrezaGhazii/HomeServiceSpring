package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.model.Services;
import ir.ghazi.service_managment.repository.ServiceRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ServiceServiceTest {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ServiceRepository serviceRepository;

    @Test
    @DisplayName("Check Save Ok")
    @Order(1)
    void saveService() {
        Services services = Services.builder()
                .name("Phone Services")
                .build();
        serviceService.saveService(services);

        Optional<Services> byName = serviceRepository.findByName(services.getName());

        assertTrue(byName.isPresent());
    }
}