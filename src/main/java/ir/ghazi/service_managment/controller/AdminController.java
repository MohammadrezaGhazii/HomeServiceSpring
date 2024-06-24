package ir.ghazi.service_managment.controller;

import ir.ghazi.service_managment.dto.admin.AdminRequest;
import ir.ghazi.service_managment.dto.admin.AdminResponse;
import ir.ghazi.service_managment.dto.service.ServiceRequest;
import ir.ghazi.service_managment.dto.service.ServiceResponse;
import ir.ghazi.service_managment.dto.subservice.SubServiceRequest;
import ir.ghazi.service_managment.dto.subservice.SubServiceResponse;
import ir.ghazi.service_managment.mapper.AdminMapper;
import ir.ghazi.service_managment.mapper.ServiceMapper;
import ir.ghazi.service_managment.mapper.SubServiceMapper;
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
    public ResponseEntity<ServiceResponse> addService(@RequestBody ServiceRequest request) {
        Services mappedService = ServiceMapper.INSTANCE.serviceSaveRequestToModel(request);
        Services savedService = serviceService.saveService(mappedService);
        return new ResponseEntity<>(ServiceMapper.INSTANCE.modelToServiceSaveResponse(savedService), HttpStatus.CREATED);
    }

    @PostMapping("/add-sub-service")
    public ResponseEntity<SubServiceResponse> addSubService(@RequestBody SubServiceRequest request) {
        SubService mappedSubService = SubServiceMapper.INSTANCE.subServiceSaveRequestToModel(request);
        SubService savedSubService = subServiceService.savaSubService(mappedSubService);
        return new ResponseEntity<>(SubServiceMapper.INSTANCE.modelToSubServiceSaveResponse(savedSubService), HttpStatus.CREATED);
    }

    @PostMapping("/add-field-specialist")
    @ResponseStatus(HttpStatus.CREATED)
    public void addFieldSpecialist(@RequestBody FieldSpecialist fieldSpecialist) {
        fieldSpecialistService.addFieldSpecialist(fieldSpecialist);
    }

    @GetMapping("/list-services")
    public void listService() {
        serviceService.listServices();
    }

    @GetMapping("/list-sub-services")
    public void listSubService() {
        subServiceService.subServiceList();
    }

    @PatchMapping("/approve-specialist")
    public void approveSpecialist(@RequestParam String email) {
        specialistService.acceptSpecialist(email);
    }
}
