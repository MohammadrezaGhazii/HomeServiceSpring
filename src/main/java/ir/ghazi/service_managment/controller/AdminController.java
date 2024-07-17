package ir.ghazi.service_managment.controller;

import ir.ghazi.service_managment.dto.admin.AdminRequest;
import ir.ghazi.service_managment.dto.admin.AdminResponse;
import ir.ghazi.service_managment.dto.client.FilterClientResponse;
import ir.ghazi.service_managment.dto.offer.AllOfferResponse;
import ir.ghazi.service_managment.dto.order.AllOrdersResponse;
import ir.ghazi.service_managment.dto.service.ServiceRequest;
import ir.ghazi.service_managment.dto.service.ServiceResponse;
import ir.ghazi.service_managment.dto.specialist.FilterSpecialistResponse;
import ir.ghazi.service_managment.dto.subservice.ListSubServiceResponse;
import ir.ghazi.service_managment.dto.subservice.SubServiceRequest;
import ir.ghazi.service_managment.dto.subservice.SubServiceResponse;
import ir.ghazi.service_managment.mapper.*;
import ir.ghazi.service_managment.model.*;
import ir.ghazi.service_managment.service.*;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    private final ServiceService serviceService;

    private final SubServiceService subServiceService;

    private final FieldSpecialistService fieldSpecialistService;

    private final SpecialistService specialistService;

    private final ClientService clientService;

    private final OrderService orderService;

    private final OfferService offerService;

    @PostMapping("/register")
    @PermitAll
    public ResponseEntity<AdminResponse> registerAdmin(@RequestBody AdminRequest request) {
        Admin mappedAdmin = AdminMapper.INSTANCE.adminSaveRequestToModel(request);
        Admin savedAdmin = adminService.registerAdmin(mappedAdmin);
        return new ResponseEntity<>(AdminMapper.INSTANCE.modelToUserSaveResponse(savedAdmin), HttpStatus.CREATED);
    }

    @GetMapping("/sign-in")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void signInAdmin(@RequestParam String email, String password) {
        adminService.signInAdmin(email, password);
    }

    @PostMapping("/add-service")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ServiceResponse> addService(@RequestBody ServiceRequest request) {
        Services mappedService = ServiceMapper.INSTANCE.serviceSaveRequestToModel(request);
        Services savedService = serviceService.saveService(mappedService);
        return new ResponseEntity<>(ServiceMapper.INSTANCE.modelToServiceSaveResponse(savedService), HttpStatus.CREATED);
    }

    @PostMapping("/add-sub-service")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<SubServiceResponse> addSubService(@RequestBody SubServiceRequest request) {
        SubService mappedSubService = SubServiceMapper.INSTANCE.subServiceSaveRequestToModel(request);
        SubService savedSubService = subServiceService.savaSubService(mappedSubService);
        return new ResponseEntity<>(SubServiceMapper.INSTANCE.modelToSubServiceSaveResponse(savedSubService), HttpStatus.CREATED);
    }

    @PostMapping("/add-field-specialist")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addFieldSpecialist(@RequestBody FieldSpecialist fieldSpecialist) {
        fieldSpecialistService.addFieldSpecialist(fieldSpecialist);
    }

    @GetMapping("/list-services")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<ServiceResponse> listService() {
        List<Services> services = serviceService.listServices();
        List<ServiceResponse> serviceResponseList = new ArrayList<>();
        for (Services service : services) {
            ServiceResponse serviceResponse = ServiceMapper.INSTANCE.modelToServiceSaveResponse(service);
            serviceResponseList.add(serviceResponse);
        }
        return serviceResponseList;
    }

    @GetMapping("/list-sub-services")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<ListSubServiceResponse> listSubService() {
        List<SubService> subServices = subServiceService.subServiceList();
        List<ListSubServiceResponse> subServiceResponseList = new ArrayList<>();
        for (SubService subService : subServices) {
            ListSubServiceResponse listSubServiceResponse = SubServiceMapper.INSTANCE.modelToListSubServiceResponse(subService);
            subServiceResponseList.add(listSubServiceResponse);
        }
        return subServiceResponseList;
    }

    @PatchMapping("/approve-specialist")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void approveSpecialist(@RequestParam String email) {
        specialistService.acceptSpecialist(email);
    }

    @GetMapping("/filter-client")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<FilterClientResponse> filterClient(@RequestParam String column, String value) {
        List<Client> clients = clientService.filterClient(column, value);
        List<FilterClientResponse> filterClientResponses = new ArrayList<>();

        for (Client client : clients) {
            FilterClientResponse filterClientResponse = ClientMapper.INSTANCE.modelToFilterClientResponse(client);
            filterClientResponses.add(filterClientResponse);
        }
        return filterClientResponses;
    }

    @GetMapping("/filter-specialist")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<FilterSpecialistResponse> filterSpecialist(@RequestParam String column, String value){
        List<Specialist> specialists = specialistService.filterSpecialist(column, value);
        List<FilterSpecialistResponse> filterSpecialistResponses = new ArrayList<>();

        for (Specialist specialist : specialists) {
            FilterSpecialistResponse filterSpecialistResponse =
                    SpecialistMapper.INSTANCE.modelToFilterSpecialistResponse(specialist);
            filterSpecialistResponses.add(filterSpecialistResponse);
        }
        return filterSpecialistResponses;
    }

    @GetMapping("/history-order-client")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<AllOrdersResponse> historyOrder(String email){
        List<Order> orders = orderService.allOrderByClient(email);
        List<AllOrdersResponse> allOrdersResponses = new ArrayList<>();
        for (Order order : orders) {
            AllOrdersResponse allOrdersResponse =
                    OrderMapper.INSTANCE.modelToAllOrdersResponse(order);
            allOrdersResponses.add(allOrdersResponse);
        }
        return allOrdersResponses;
    }

    @GetMapping("/history-offer-specialist")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<AllOfferResponse> historyOffers(String email){
        List<Offer> offers = offerService.allOffersBySpecialist(email);
        List<AllOfferResponse> allOfferResponses = new ArrayList<>();
        for (Offer offer : offers) {
            AllOfferResponse allOfferResponse =
                    OfferMapper.INSTANCE.modelToAllOfferResponse(offer);
            allOfferResponses.add(allOfferResponse);
        }
        return allOfferResponses;
    }

    @GetMapping("/register-date-specialist")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public LocalDate registerSpecialist(String email){
        return specialistService.dateRegister(email);
    }

    @GetMapping("/register-date-client")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public LocalDate registerClient(String email){
        return clientService.registerDate(email);
    }

    @GetMapping("/number-order-client")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public int numberOfOrderByClient(String email){
        return orderService.numberOfOrderByClient(email);
    }

    @GetMapping("/number-offer-specialist")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public int numberOfOfferBySpecialist(String email){
        return offerService.numberOfOfferBySpecialist(email);
    }
}
