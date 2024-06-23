package ir.ghazi.service_managment.controller;

import ir.ghazi.service_managment.dto.specialist.SpecialistRequest;
import ir.ghazi.service_managment.dto.specialist.SpecialistResponse;
import ir.ghazi.service_managment.mapper.SpecialistMapper;
import ir.ghazi.service_managment.model.Specialist;
import ir.ghazi.service_managment.service.SpecialistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
