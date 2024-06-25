package ir.ghazi.service_managment.controller;

import ir.ghazi.service_managment.dto.client.ClientRequest;
import ir.ghazi.service_managment.dto.client.ClientResponse;
import ir.ghazi.service_managment.dto.order.OrderRequest;
import ir.ghazi.service_managment.dto.order.OrderResponse;
import ir.ghazi.service_managment.mapper.ClientMapper;
import ir.ghazi.service_managment.mapper.OrderMapper;
import ir.ghazi.service_managment.model.Client;
import ir.ghazi.service_managment.model.Offer;
import ir.ghazi.service_managment.model.Order;
import ir.ghazi.service_managment.service.ClientService;
import ir.ghazi.service_managment.service.OfferService;
import ir.ghazi.service_managment.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    private final OrderService orderService;

    private final OfferService offerService;

    @PostMapping("/register-client")
    public ResponseEntity<ClientResponse> registerClient(@RequestBody ClientRequest request) {
        Client mappedClient = ClientMapper.INSTANCE.clientSaveRequestToModel(request);
        Client savedClient = clientService.saveClient(mappedClient);
        return new ResponseEntity<>(ClientMapper.INSTANCE.modelToClientSaveResponse(savedClient), HttpStatus.CREATED);
    }

    @GetMapping("/sign-in-client")
    public void signInClient(@RequestParam String email, String password) {
        clientService.clientSignIn(email, password);
    }

    @PatchMapping("change-password")
    public void changePassword(@RequestParam String email, String passwordRequest) {
        Client client = clientService.findByEmail(email);
        client.setPassword(passwordRequest);
        clientService.updateClient(client);
    }

    @PostMapping("/add-order")
    public ResponseEntity<OrderResponse> addOrder(@RequestBody OrderRequest request) {
        Order mappedOrder = OrderMapper.INSTANCE.orderSaveRequestToModel(request);
        Order savedOrder = orderService.addOrder(mappedOrder);
        return new ResponseEntity<>(OrderMapper.INSTANCE.modelToOrderSaveResponse(savedOrder), HttpStatus.CREATED);
    }

    @GetMapping("/list-by-price")
    public void listByPrice(@RequestParam Long id) {
        offerService.listByOfferPrice(id);
    }

    @GetMapping("/list-by-score")
    public void listByScore(@RequestParam Long id) {
        offerService.listByScoreSpecialist(id);
    }

    @PatchMapping("/choose-specialist")
    public void chooseSpecialist(Long id) {
        Offer offer = offerService.findById(id);
        offerService.chooseOfferFromClient(offer);
    }

    @PatchMapping("/change-to-started")
    public void changeStarted(Long id) {
        orderService.addStartWorkFromClient(id);
    }

    @PatchMapping("/change-to-done")
    public void changeDone(Long id){
        orderService.changeSituationOrderToEnd(id);
    }

    @PatchMapping("/pay-by-wallet")
    public void payWallet(Long id){
        orderService.paymentWallet(id);
    }


}
