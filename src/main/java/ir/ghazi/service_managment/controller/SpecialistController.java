package ir.ghazi.service_managment.controller;

import ir.ghazi.service_managment.dto.specialist.SpecialistRequest;
import ir.ghazi.service_managment.dto.specialist.SpecialistResponse;
import ir.ghazi.service_managment.mapper.SpecialistMapper;
import ir.ghazi.service_managment.model.Specialist;
import ir.ghazi.service_managment.service.SpecialistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SpecialistController {

    private final SpecialistService specialistService;

    @PostMapping("/register-specialist")
    public ResponseEntity<SpecialistResponse> registerClient(@RequestBody SpecialistRequest request , String filePath) {
        Specialist mappedSpecialist = SpecialistMapper.INSTANCE.specialistSaveRequestToModel(request);
        Specialist savedSpecialist = specialistService.saveSpecialist(mappedSpecialist , filePath);
        return new ResponseEntity<>(SpecialistMapper.INSTANCE.modelToSpecialistSaveResponse(savedSpecialist),
                HttpStatus.CREATED);
    }

    @GetMapping("/sign-in-specialist")
    public void signInSpecialist(@RequestParam String email , String password){
        specialistService.specialistSignIn(email, password);
    }

    @PatchMapping("/change-password-specialist")
    public void changePassword(@RequestParam String email , String passwordRequest){
        Specialist specialist = specialistService.findByEmail(email);
        specialist.setPassword(passwordRequest);
        specialistService.updateSpecialist(specialist);
    }
}
