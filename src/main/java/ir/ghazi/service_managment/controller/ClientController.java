package ir.ghazi.service_managment.controller;

import ir.ghazi.service_managment.dto.client.ClientRequest;
import ir.ghazi.service_managment.dto.client.ClientResponse;
import ir.ghazi.service_managment.dto.client.FilterClientResponse;
import ir.ghazi.service_managment.dto.order.OrderRequest;
import ir.ghazi.service_managment.dto.order.OrderResponse;
import ir.ghazi.service_managment.dto.payment.PaymentRequest;
import ir.ghazi.service_managment.dto.rate.RateRequest;
import ir.ghazi.service_managment.dto.rate.RateResponse;
import ir.ghazi.service_managment.dto.subservice.ListSubServiceResponse;
import ir.ghazi.service_managment.mapper.ClientMapper;
import ir.ghazi.service_managment.mapper.OrderMapper;
import ir.ghazi.service_managment.mapper.RateMapper;
import ir.ghazi.service_managment.model.Client;
import ir.ghazi.service_managment.model.Offer;
import ir.ghazi.service_managment.model.Order;
import ir.ghazi.service_managment.model.Rate;
import ir.ghazi.service_managment.service.ClientService;
import ir.ghazi.service_managment.service.OfferService;
import ir.ghazi.service_managment.service.OrderService;
import ir.ghazi.service_managment.service.RateService;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    private final OrderService orderService;

    private final OfferService offerService;

    private final RateService rateService;

    @PostMapping("/register")
    @PermitAll
    public ResponseEntity<ClientResponse> registerClient(@RequestBody ClientRequest request) {
        Client mappedClient = ClientMapper.INSTANCE.clientSaveRequestToModel(request);
        Client savedClient = clientService.saveClient(mappedClient);
        return new ResponseEntity<>(ClientMapper.INSTANCE.modelToClientSaveResponse(savedClient), HttpStatus.CREATED);
    }

    @GetMapping("/sign-in")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public void signInClient(@RequestParam String email, String password) {
        clientService.clientSignIn(email, password);
    }

    @PatchMapping("change-password")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public void changePassword(@RequestParam String email, String passwordRequest) {
        Client client = clientService.findByEmail(email);
        client.setPassword(passwordRequest);
        clientService.updateClient(client);
    }

    @PostMapping("/add-order")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public ResponseEntity<OrderResponse> addOrder(@RequestBody OrderRequest request) {
        Order mappedOrder = OrderMapper.INSTANCE.orderSaveRequestToModel(request);
        Order savedOrder = orderService.addOrder(mappedOrder);
        return new ResponseEntity<>(OrderMapper.INSTANCE.modelToOrderSaveResponse(savedOrder), HttpStatus.CREATED);
    }

    @GetMapping("/list-by-price")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public void listByPrice(@RequestParam Long id) {
        offerService.listByOfferPrice(id);
    }

    @GetMapping("/list-by-score")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public void listByScore(@RequestParam Long id) {
        offerService.listByScoreSpecialist(id);
    }

    @PatchMapping("/choose-specialist")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public void chooseSpecialist(Long id) {
        Offer offer = offerService.findById(id);
        offerService.chooseOfferFromClient(offer);
    }

    @PatchMapping("/change-to-started")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public void changeStarted(Long id) {
        orderService.addStartWorkFromClient(id);
    }

    @PatchMapping("/change-to-done")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public void changeDone(Long id) {
        orderService.changeSituationOrderToEnd(id);
    }

    @PatchMapping("/pay-by-wallet")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public void payWallet(Long id) {
        orderService.paymentWallet(id);
    }

    @PostMapping("/add-rate")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public ResponseEntity<RateResponse> addRate(@RequestBody RateRequest request) {
        Rate mappedRate = RateMapper.INSTANCE.rateSaveRequestToModel(request);
        Rate savedRate = rateService.saveRate(mappedRate);
        return new ResponseEntity<>(RateMapper.INSTANCE.modelToRateSaveResponse(savedRate), HttpStatus.CREATED);
    }
}
