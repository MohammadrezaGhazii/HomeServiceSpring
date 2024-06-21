package ir.ghazi.service_managment.controller;

import ir.ghazi.service_managment.dto.client.ClientRequest;
import ir.ghazi.service_managment.dto.client.ClientResponse;
import ir.ghazi.service_managment.mapper.ClientMapper;
import ir.ghazi.service_managment.model.Client;
import ir.ghazi.service_managment.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/register-client")
    public ResponseEntity<ClientResponse> registerClient(@RequestBody ClientRequest request) {
        Client mappedClient = ClientMapper.INSTANCE.clientSaveRequestToModel(request);
        Client savedClient = clientService.saveClient(mappedClient);
        return new ResponseEntity<>(ClientMapper.INSTANCE.modelToClientSaveResponse(savedClient), HttpStatus.CREATED);
    }
}
