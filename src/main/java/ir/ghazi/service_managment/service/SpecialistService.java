package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.base.exception.NotFoundException;
import ir.ghazi.service_managment.base.exception.ValidationException;
import ir.ghazi.service_managment.enums.SpecialistSituation;
import ir.ghazi.service_managment.model.Client;
import ir.ghazi.service_managment.model.CreditSpecialist;
import ir.ghazi.service_managment.model.Specialist;
import ir.ghazi.service_managment.repository.SpecialistRepository;
import ir.ghazi.service_managment.utilities.Validation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SpecialistService {

    private final SpecialistRepository specialistRepository;

    private final CreditSpecialistService creditSpecialistService;

    private final EntityManager entityManager;

    private final BCryptPasswordEncoder passwordEncoder;

    public Specialist saveSpecialist(Specialist specialist , String filePath) {
        byte[] bytes = uploadPhoto(filePath);
        specialist.setImage(bytes);
        if (!Validation.isNameValid(specialist.getFirstName())) {
            throw new ValidationException("Firstname should just in letters");
        } else if (!Validation.isNameValid(specialist.getLastName())) {
            throw new ValidationException("Lastname should just in letters");
        } else if (!Validation.isEmailValid(specialist.getEmail())) {
            throw new ValidationException("Email is not valid ! || Enter like this : johnsons@gmail.com");
        } else if (!Validation.isPasswordValid(specialist.getPassword())) {
            throw new ValidationException("password is not strong ! || Enter like : Aa@12345");
        } else if (specialistRepository.findByEmail(specialist.getEmail()).isPresent()) {
            log.warn("This " + specialist.getEmail() + " is already registered !!!");
        } else if (filePath == null) {
            log.warn("This " + specialist.getEmail() + " photo is not available !!!");
        } else {
            specialist.setPassword(passwordEncoder.encode(specialist.getPassword()));
            specialistRepository.save(specialist);
            CreditSpecialist creditSpecialist = CreditSpecialist.builder()
                    .specialist(specialist)
                    .build();
            creditSpecialistService.addCredit(creditSpecialist);
            log.info(specialist.getEmail() + " has successfully add !");
        }
        return specialist;
    }

    private String getContentType(File file) {
        String contentType = "unknown";
        String fileName = file.getName();
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (fileExtension.equalsIgnoreCase("jpg") || fileExtension.equalsIgnoreCase("jpeg")) {
            contentType = "image/jpeg";
        } else {
            log.error("Error: File is not a valid JPG file.");
        }
        return contentType;
    }

    public byte[] uploadPhoto(String filePath){
        File file = new File(filePath);
        if (!file.exists() || !file.isFile())
            throw new ValidationException("Invalid file path. Please try again.");
        if (file.length() >= 300 * 1024)
            throw new ValidationException("File size exceeds 300KB. Please upload a smaller file.");
        String fileType = getContentType(file);
        if (!fileType.equals("image/jpeg"))
            throw new ValidationException("Invalid file format. Please upload a JPG file.");
        try {
            return Files.readAllBytes(file.toPath());
        }
        catch (IOException e){
            throw new ValidationException("Something Wrong");
        }
    }

    public Optional<Specialist> specialistSignIn(String email, String password) {
        try {
            Optional<Specialist> find = specialistRepository.findByEmailAndPassword(email, password);
            find.orElseThrow(() -> new NotFoundException("Entity not found"));
            log.info("Welcome " + find.get().getFirstName());
            return find;
        } catch (Exception e) {
            log.error("An error in Specialist sign in ");
            return Optional.empty();
        }
    }

    public void updateSpecialist(Specialist specialist) {
        if (!Validation.isPasswordValid(specialist.getPassword())) {
            log.warn("password is not strong ! || Enter like : Aa@12345");
        } else
            specialistRepository.save(specialist);
    }

    public void acceptSpecialist(String email) {
        Specialist specialist = specialistRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Specialist with " + email + " does not found"));

        specialist.setSituation(SpecialistSituation.APPROVED);
        specialistRepository.save(specialist);
    }

    public boolean checkAccepted(String email) {
        Specialist specialist = specialistRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Specialist with " + email + " does not found"));

        if (specialist.getSituation().equals(SpecialistSituation.NEW_JOINER)) {
            log.error("You should wait until admin Approve your account");
            return false;
        } else
            return true;
    }

    public Specialist findByEmail(String email){
        Optional<Specialist> byEmail = specialistRepository.findByEmail(email);
        Specialist specialist = null;
        if (byEmail.isPresent())
            specialist = byEmail.get();
        else
            throw new ValidationException("Email is not available");
        return specialist;
    }

    public List<Specialist> filterSpecialist(String column , String value){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Specialist> query = criteriaBuilder.createQuery(Specialist.class);
        Root<Specialist> clientRoot = query.from(Specialist.class);

        Predicate columnFilter = criteriaBuilder.equal(clientRoot.get(column), value);
        query.where(criteriaBuilder.and(columnFilter));

        return entityManager.createQuery(query).getResultList();
    }
}
