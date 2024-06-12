package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.model.SubService;
import ir.ghazi.service_managment.repository.ServiceRepository;
import ir.ghazi.service_managment.repository.SubServiceRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
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
                .service(serviceRepository.findByName("Home Services").get())
                .build();
        subServiceService.savaSubService(subService);

        Optional<SubService> byName = subServiceRepository.findByName(subService.getName());

        SubService subService1 = SubService.builder()
                .name("phone repairs")
                .basePrice(300000D)
                .description("all repairs for your phone")
                .service(serviceRepository.findByName("Phone Services").get())
                .build();
        subServiceService.savaSubService(subService1);

        Optional<SubService> byName1 = subServiceRepository.findByName(subService1.getName());

        assertTrue(byName.isPresent());
        assertTrue(byName1.isPresent());
    }

    @Test
    @DisplayName("Check Save with duplicate name")
    @Order(2)
    void savaDuplicateSubService() {
        SubService subService = SubService.builder()
                .name("Piping")
                .basePrice(25000D)
                .description("check your house piping")
                .service(serviceRepository.findByName("Home Services").get())
                .build();
        subServiceService.savaSubService(subService);

        Optional<SubService> byId = subServiceRepository.findById(3L);

        assertFalse(byId.isPresent());
    }
    @Test
    @DisplayName("Check list sub services")
    void listSubServices(){
        List<SubService> subServiceList = subServiceService.subServiceList();
        int size = subServiceList.size();

        assertEquals(size , 2);
    }
}