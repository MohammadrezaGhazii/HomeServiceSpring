package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.model.Services;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class ServiceServiceTest {
    @Autowired
    private ServiceService serviceService;

    @Test
    @DisplayName("Save service")
    void saveService() {
        Services services = Services.builder()
                .name("car Services")
                .build();
        serviceService.saveService(services);
    }
}