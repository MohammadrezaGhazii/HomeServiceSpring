package ir.ghazi.service_managment.controller;

import ir.ghazi.service_managment.dto.admin.AdminRequest;
import ir.ghazi.service_managment.dto.admin.AdminResponse;
import ir.ghazi.service_managment.mapper.AdminMapper;
import ir.ghazi.service_managment.model.Admin;
import ir.ghazi.service_managment.model.FieldSpecialist;
import ir.ghazi.service_managment.model.Services;
import ir.ghazi.service_managment.model.SubService;
import ir.ghazi.service_managment.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    private final ServiceService serviceService;

    private final SubServiceService subServiceService;

    private final FieldSpecialistService fieldSpecialistService;

    private final SpecialistService specialistService;

    @PostMapping("/register-admin")
    public ResponseEntity<AdminResponse> registerAdmin(@RequestBody AdminRequest request) {
        Admin mappedAdmin = AdminMapper.INSTANCE.adminSaveRequestToModel(request);
        Admin savedAdmin = adminService.registerAdmin(mappedAdmin);
        return new ResponseEntity<>(AdminMapper.INSTANCE.modelToUserSaveResponse(savedAdmin), HttpStatus.CREATED);
    }

    @GetMapping("/sign-in-admin")
    public void signInAdmin(@RequestParam String email, String password) {
        adminService.signInAdmin(email, password);
    }

    @PostMapping("/add-service")
    @ResponseStatus(HttpStatus.CREATED)
    public void addService(@RequestBody Services services){
        serviceService.saveService(services);
    }

    @PostMapping("/add-sub-service")
    @ResponseStatus(HttpStatus.CREATED)
    public void addSubService(@RequestBody SubService subService){
        subServiceService.savaSubService(subService);
    }

    @PostMapping("/add-field-specialist")
    @ResponseStatus(HttpStatus.CREATED)
    public void addFieldSpecialist(@RequestBody FieldSpecialist fieldSpecialist){
        fieldSpecialistService.addFieldSpecialist(fieldSpecialist);
    }

    @GetMapping("/list-services")
    public void listService(){
        serviceService.listServices();
    }

    @GetMapping("/list-sub-services")
    public void listSubService(){
        subServiceService.subServiceList();
    }

    @PatchMapping("/approve-specialist")
    public void approveSpecialist(@RequestParam String email){
        specialistService.acceptSpecialist(email);
    }
}
