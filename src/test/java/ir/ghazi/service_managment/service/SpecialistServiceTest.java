package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.enums.SpecialistSituation;
import ir.ghazi.service_managment.model.Client;
import ir.ghazi.service_managment.model.Specialist;
import ir.ghazi.service_managment.repository.SpecialistRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SpecialistServiceTest {

    @Autowired
    private SpecialistService specialistService;
    @Autowired
    private SpecialistRepository specialistRepository;

    @Test
    @DisplayName("Check Save Ok")
    @Order(1)
    void saveSpecialist() {
        String filePath = "C:\\Users\\user\\Desktop\\worker\\download.jpeg";
        byte[] bytesImage = specialistService.uploadPhoto(filePath);
        Specialist specialist = Specialist.builder()
                .firstName("mohammadreza")
                .lastName("ghazi")
                .email("mohammadreza@gmail.com")
                .password("Aa@12345")
                .registerDate(LocalDate.now())
                .image(bytesImage)
                .score(0D)
                .situation(SpecialistSituation.NEW_JOINER)
                .build();
        Specialist saveSpecialist = specialistService.saveSpecialist(specialist);

        Optional<Specialist> byEmail = specialistRepository.findByEmail(saveSpecialist.getEmail());
        assertNotNull(byEmail);
        assertEquals("mohammadreza", byEmail.get().getFirstName());
        assertEquals("ghazi", byEmail.get().getLastName());
        assertEquals("mohammadreza@gmail.com", byEmail.get().getEmail());
        assertEquals("Aa@12345", byEmail.get().getPassword());
    }

    @Test
    @DisplayName("Check Validation name")
    void saveSpecialistValidationName() {
        String filePath = "C:\\Users\\user\\Desktop\\worker\\download.jpeg";
        byte[] bytesImage = specialistService.uploadPhoto(filePath);
        Specialist specialist = Specialist.builder()
                .firstName("1234")
                .lastName("ghazi")
                .email("test9@gmail.com")
                .password("Aa@12345")
                .registerDate(LocalDate.now())
                .image(bytesImage)
                .score(0D)
                .situation(SpecialistSituation.NEW_JOINER)
                .build();
        Specialist saveSpecialist = specialistService.saveSpecialist(specialist);

        Optional<Specialist> byEmail = specialistRepository.findByEmail(saveSpecialist.getEmail());
        assertFalse(byEmail.isPresent());
    }

    @Test
    @DisplayName("Check Validation last name")
    void saveSpecialistValidationLastName() {
        String filePath = "C:\\Users\\user\\Desktop\\worker\\download.jpeg";
        byte[] bytesImage = specialistService.uploadPhoto(filePath);
        Specialist specialist = Specialist.builder()
                .firstName("mohammad")
                .lastName("12345")
                .email("test9@gmail.com")
                .password("Aa@12345")
                .registerDate(LocalDate.now())
                .image(bytesImage)
                .score(0D)
                .situation(SpecialistSituation.NEW_JOINER)
                .build();
        Specialist saveSpecialist = specialistService.saveSpecialist(specialist);

        Optional<Specialist> byEmail = specialistRepository.findByEmail(saveSpecialist.getEmail());
        assertFalse(byEmail.isPresent());
    }

    @Test
    @DisplayName("Check Validation email")
    void saveSpecialistValidationEmail() {
        String filePath = "C:\\Users\\user\\Desktop\\worker\\download.jpeg";
        byte[] bytesImage = specialistService.uploadPhoto(filePath);
        Specialist specialist = Specialist.builder()
                .firstName("mohammad")
                .lastName("ghazi")
                .email("asghar55")
                .password("Aa@12345")
                .registerDate(LocalDate.now())
                .image(bytesImage)
                .score(0D)
                .situation(SpecialistSituation.NEW_JOINER)
                .build();
        Specialist saveSpecialist = specialistService.saveSpecialist(specialist);

        Optional<Specialist> byEmail = specialistRepository.findByEmail(saveSpecialist.getEmail());
        assertFalse(byEmail.isPresent());
    }

    @Test
    @DisplayName("Check Validation password")
    void saveSpecialistValidationPassword() {
        String filePath = "C:\\Users\\user\\Desktop\\worker\\download.jpeg";
        byte[] bytesImage = specialistService.uploadPhoto(filePath);
        Specialist specialist = Specialist.builder()
                .firstName("mohammad")
                .lastName("ghazi")
                .email("test9@gmail.com")
                .password("aa22")
                .registerDate(LocalDate.now())
                .image(bytesImage)
                .score(0D)
                .situation(SpecialistSituation.NEW_JOINER)
                .build();
        Specialist saveSpecialist = specialistService.saveSpecialist(specialist);

        Optional<Specialist> byEmail = specialistRepository.findByEmail(saveSpecialist.getEmail());
        assertFalse(byEmail.isPresent());
    }

    @Test
    @DisplayName("Check Wrong Format Image")
    void savaWithWrongFormatImage() {
        String filePath = "C:\\Users\\user\\Desktop\\worker\\codeyad-wallpaper-3.png";
        byte[] bytesImage = specialistService.uploadPhoto(filePath);
        Specialist specialist = Specialist.builder()
                .firstName("mohammadreza")
                .lastName("ghazi")
                .email("test2@gmail.com")
                .password("Aa@12345")
                .registerDate(LocalDate.now())
                .image(bytesImage)
                .score(0D)
                .situation(SpecialistSituation.NEW_JOINER)
                .build();
        Specialist saveSpecialist = specialistService.saveSpecialist(specialist);
        Optional<Specialist> byEmail = specialistRepository.findByEmail(saveSpecialist.getEmail());
        assertFalse(byEmail.isPresent());
    }

    @Test
    @DisplayName("Check Wrong size Image")
    void savaWithWrongSizeImage() {
        String filePath = "C:\\Users\\user\\Desktop\\worker\\up300.jpg";
        byte[] bytesImage = specialistService.uploadPhoto(filePath);
        Specialist specialist = Specialist.builder()
                .firstName("mohammadreza")
                .lastName("ghazi")
                .email("test3@gmail.com")
                .password("Aa@12345")
                .registerDate(LocalDate.now())
                .image(bytesImage)
                .score(0D)
                .situation(SpecialistSituation.NEW_JOINER)
                .build();
        Specialist saveSpecialist = specialistService.saveSpecialist(specialist);
        Optional<Specialist> byEmail = specialistRepository.findByEmail(saveSpecialist.getEmail());
        assertFalse(byEmail.isPresent());
    }

    @Test
    @DisplayName("Check Invalid file path Image")
    void savaWithWrongLocImage() {
        String filePath = "123456aaaaa";
        byte[] bytesImage = specialistService.uploadPhoto(filePath);
        Specialist specialist = Specialist.builder()
                .firstName("mohammadreza")
                .lastName("ghazi")
                .email("test4@gmail.com")
                .password("Aa@12345")
                .registerDate(LocalDate.now())
                .image(bytesImage)
                .score(0D)
                .situation(SpecialistSituation.NEW_JOINER)
                .build();
        Specialist saveSpecialist = specialistService.saveSpecialist(specialist);
        Optional<Specialist> byEmail = specialistRepository.findByEmail(saveSpecialist.getEmail());
        assertFalse(byEmail.isPresent());
    }

    @Test
    @Order(2)
    @DisplayName("Check for specialist sign in")
    void specialistSignIn() {
        Optional<Specialist> client = specialistService.
                specialistSignIn("mohammadreza@gmail.com", "Aa@12345");
        assertTrue(client.isPresent());
    }

    @Test
    @DisplayName("Check for specialist not sign in")
    void specialistSignInError() {
        Optional<Specialist> client = specialistService.
                specialistSignIn("notsign@gmail.com", "Aa@12345");
        assertFalse(client.isPresent());
    }

    @Test
    @DisplayName("Check for update password")
    void updatePassword(){
        Optional<Specialist> byEmail = specialistRepository.findByEmail("mohammadreza@gmail.com");
        Specialist specialist = byEmail.get();
        specialist.setPassword("Mm@12345");
        specialistService.updateSpecialist(specialist);

        assertEquals(specialist.getPassword(),"Mm@12345" );
    }
}