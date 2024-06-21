package ir.ghazi.service_managment.controller;

import ir.ghazi.service_managment.dto.admin.AdminRequest;
import ir.ghazi.service_managment.dto.admin.AdminResponse;
import ir.ghazi.service_managment.mapper.AdminMapper;
import ir.ghazi.service_managment.model.Admin;
import ir.ghazi.service_managment.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/register-admin")
    public ResponseEntity<AdminResponse> registerAdmin(@RequestBody AdminRequest request){
        Admin mappedAdmin = AdminMapper.INSTANCE.adminSaveRequestToModel(request);
        Admin savedAdmin = adminService.registerAdmin(mappedAdmin);
        return new ResponseEntity<>(AdminMapper.INSTANCE.modelToUserSaveResponse(savedAdmin), HttpStatus.CREATED);
    }

    @GetMapping("/sign-in-admin")
    public void signInAdmin(@RequestParam String email, String password){
        adminService.signInAdmin(email,password);
    }
}
