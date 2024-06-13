package ir.ghazi.service_managment.service;

import ir.ghazi.service_managment.base.exception.NotFoundException;
import ir.ghazi.service_managment.model.Specialist;
import ir.ghazi.service_managment.repository.SpecialistRepository;
import ir.ghazi.service_managment.utilities.Validation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Files;
import java.util.Optional;

@Slf4j
@Service
public class SpecialistService {
    private final SpecialistRepository specialistRepository;

    public SpecialistService(SpecialistRepository specialistRepository) {
        this.specialistRepository = specialistRepository;
    }

    public Specialist saveSpecialist(Specialist specialist) {
        if (!Validation.isNameValid(specialist.getFirstName())) {
            log.warn("Firstname should just in letters");
        } else if (!Validation.isNameValid(specialist.getLastName())) {
            log.warn("Lastname should just in letters");
        } else if (!Validation.isEmailValid(specialist.getEmail())) {
            log.warn("Email is not valid ! || Enter like this : johnsons@gmail.com");
        } else if (!Validation.isPasswordValid(specialist.getPassword())) {
            log.warn("password is not strong ! || Enter like : Aa@12345");
        } else if (specialistRepository.findByEmail(specialist.getEmail()).isPresent()) {
            log.warn("This " + specialist.getEmail() + " is already registered !!!");
        } else if (specialist.getImage() == null) {
            log.warn("This " + specialist.getEmail() + " photo is not available !!!");
        } else {
            specialistRepository.save(specialist);
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

    public byte[] uploadPhoto(String filePath) {
        byte[] photo = null;
        while (true) {
            File file = new File(filePath);
            if (file.exists() && file.isFile()) {
                if (file.length() <= 300 * 1024) {
                    String fileType = getContentType(file);
                    if (fileType.equals("image/jpeg")) {
                        try {
                            photo = Files.readAllBytes(file.toPath());
                            break;
                        } catch (Exception e) {
                            log.error("Error reading file: " + e.getMessage());
                            break;
                        }
                    } else {
                        log.error("Invalid file format. Please upload a JPG file.");
                        break;
                    }
                } else {
                    log.error("File size exceeds 300KB. Please upload a smaller file.");
                    break;
                }
            } else {
                log.error("Invalid file path. Please try again.");
                break;
            }
        }
        return photo;
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
}
