package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.base.exception.NotFoundException;
import ir.ghazi.service_managment.base.exception.ValidationException;
import ir.ghazi.service_managment.enums.SpecialistSituation;
import ir.ghazi.service_managment.model.Specialist;
import ir.ghazi.service_managment.repository.SpecialistRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

        String filePath1 = "C:\\Users\\user\\Desktop\\worker\\download.jpeg";
        byte[] bytesImage1 = specialistService.uploadPhoto(filePath1);
        Specialist specialist1 = Specialist.builder()
                .firstName("ali")
                .lastName("ghazi")
                .email("ali@gmail.com")
                .password("Aa@12345")
                .registerDate(LocalDate.now())
                .image(bytesImage1)
                .score(0D)
                .situation(SpecialistSituation.NEW_JOINER)
                .build();
        specialistService.saveSpecialist(specialist1);

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
        Specialist specialist = Specialist.builder()
                .firstName("1234")
                .lastName("ghazi")
                .email("test9@gmail.com")
                .password("Aa@12345")
                .registerDate(LocalDate.now())
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
        Specialist specialist = Specialist.builder()
                .firstName("mohammad")
                .lastName("12345")
                .email("test9@gmail.com")
                .password("Aa@12345")
                .registerDate(LocalDate.now())
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
        Specialist specialist = Specialist.builder()
                .firstName("mohammad")
                .lastName("ghazi")
                .email("asghar55")
                .password("Aa@12345")
                .registerDate(LocalDate.now())
                .score(0D)
                .situation(SpecialistSituation.NEW_JOINER)
                .build();
        Specialist saveSpecialist = specialistService.saveSpecialist(specialist);

        Optional<Specialist> byEmail = specialistRepository.findByEmail(saveSpecialist.getEmail());
        assertFalse(byEmail.isPresent());
    }

    @Test
    @DisplayName("Check Duplicate email")
    void saveSpecialistDuplicateEmail() {
        Specialist specialist = Specialist.builder()
                .firstName("mohammad")
                .lastName("ghazi")
                .email("mohammadreza@gmail.com")
                .password("Aa@12345")
                .registerDate(LocalDate.now())
                .score(0D)
                .situation(SpecialistSituation.NEW_JOINER)
                .build();
        Specialist saveSpecialist = specialistService.saveSpecialist(specialist);

        Optional<Specialist> byEmail = specialistRepository.findByEmail(saveSpecialist.getEmail());
        assertTrue(byEmail.isPresent());
    }

    @Test
    @DisplayName("Check Validation password")
    void saveSpecialistValidationPassword() {
        Specialist specialist = Specialist.builder()
                .firstName("mohammad")
                .lastName("ghazi")
                .email("test9@gmail.com")
                .password("aa22")
                .registerDate(LocalDate.now())
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
        Specialist specialist = Specialist.builder()
                .firstName("mohammadreza")
                .lastName("ghazi")
                .email("test2@gmail.com")
                .password("Aa@12345")
                .registerDate(LocalDate.now())
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
        Specialist specialist = Specialist.builder()
                .firstName("mohammadreza")
                .lastName("ghazi")
                .email("test3@gmail.com")
                .password("Aa@12345")
                .registerDate(LocalDate.now())
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
        ValidationException validationException = assertThrows
                (ValidationException.class, () -> specialistService.saveSpecialist(specialist));

        assertEquals(validationException.getMessage() , "Invalid file path. Please try again.");
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
    void updatePassword() {
        Optional<Specialist> byEmail = specialistRepository.findByEmail("mohammadreza@gmail.com");
        Specialist specialist = byEmail.get();
        specialist.setPassword("Mm@12345");
        specialistService.updateSpecialist(specialist);

        assertEquals(specialist.getPassword(), "Mm@12345");
    }

    @Test
    @DisplayName("Check for dont update password")
    void dontUpdatePassword() {
        Optional<Specialist> byEmail = specialistRepository.findByEmail("mohammadreza@gmail.com");
        Specialist specialist = byEmail.get();
        specialist.setPassword("123aaa");
        specialistService.updateSpecialist(specialist);

        assertNotEquals(specialist.getPassword(), "Mm@12345");
    }

    @Test
    @DisplayName("approve specialist")
    void approveSpecialist() {
        specialistService.acceptSpecialist("ali@gmail.com");

        Specialist specialist = specialistRepository.findByEmail("ali@gmail.com")
                .orElseThrow(() -> new NotFoundException("Not found"));

        assertEquals(specialist.getSituation(), SpecialistSituation.APPROVED);
    }

    @Test
    @DisplayName("check account situation")
    void checkAccountSituation() {
        boolean check = specialistService.checkAccepted("ali@gmail.com");

        assertTrue(check);
    }
}