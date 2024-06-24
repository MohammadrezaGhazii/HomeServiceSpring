package ir.ghazi.service_managment.controller;

import ir.ghazi.service_managment.dto.client.ClientRequest;
import ir.ghazi.service_managment.dto.client.ClientResponse;
import ir.ghazi.service_managment.mapper.ClientMapper;
import ir.ghazi.service_managment.model.Client;
import ir.ghazi.service_managment.model.Order;
import ir.ghazi.service_managment.service.ClientService;
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

    @PostMapping("/register-client")
    public ResponseEntity<ClientResponse> registerClient(@RequestBody ClientRequest request) {
        Client mappedClient = ClientMapper.INSTANCE.clientSaveRequestToModel(request);
        Client savedClient = clientService.saveClient(mappedClient);
        return new ResponseEntity<>(ClientMapper.INSTANCE.modelToClientSaveResponse(savedClient), HttpStatus.CREATED);
    }

    @GetMapping("/sign-in-client")
    public void signInClient(@RequestParam String email , String password){
        clientService.clientSignIn(email, password);
    }

    @PatchMapping("change-password")
    public void changePassword(@RequestParam String email , String passwordRequest){
        Client client = clientService.findByEmail(email);
        client.setPassword(passwordRequest);
        clientService.updateClient(client);
    }

    @PostMapping("/add-order")
    public void addOrder(@RequestBody Order order){
        orderService.addOrder(order);
    }
}
