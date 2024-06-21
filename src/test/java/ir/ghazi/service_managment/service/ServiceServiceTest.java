//package ir.ghazi.service_managment.service;
//
//import ir.ghazi.service_managment.model.Services;
//import ir.ghazi.service_managment.repository.ServiceRepository;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//class ServiceServiceTest {
//
//    @Autowired
//    private ServiceService serviceService;
//
//    @Autowired
//    private ServiceRepository serviceRepository;
//
//    @Test
//    @DisplayName("Check Save Ok")
//    @Order(1)
//    void saveService() {
//        Services services = Services.builder()
//                .name("Phone Services")
//                .build();
//        serviceService.saveService(services);
//
//        Optional<Services> byName = serviceRepository.findByName(services.getName());
//
//        Services services1 = Services.builder()
//                .name("Home Services")
//                .build();
//        serviceService.saveService(services1);
//
//        Optional<Services> byName1 = serviceRepository.findByName(services1.getName());
//
//        assertTrue(byName.isPresent());
//        assertTrue(byName1.isPresent());
//    }
//
//    @Test
//    @DisplayName("Check List Service")
//    void checkList() {
//        List<Services> services = serviceService.listServices();
//        int sizeList = services.size();
//
//        assertEquals(sizeList, 2);
//    }
//}