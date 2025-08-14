package com.mbm.clientservice.controller;

import com.mbm.clientservice.dto.ClientRequestDTO;
import com.mbm.clientservice.dto.ClientResponseDTO;
import com.mbm.clientservice.dto.validators.CreateClientValidationGroup;
import com.mbm.clientservice.service.ClientService;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController // We define the class as a rest controller
@RequestMapping("/clients") //and this controller is going to handle all the requests coming from or going to this route "https://localhost:4000/clients"

public class ClientController {
    // Dependency Injection
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> getClients() {
        List<ClientResponseDTO> clients = clientService.getClients();
        return ResponseEntity.ok().body(clients);
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> createClient(@Validated({Default.class, CreateClientValidationGroup.class}) @RequestBody ClientRequestDTO clientRequestDTO) {
        ClientResponseDTO clientResponseDTO = clientService.createClient(clientRequestDTO);
        return ResponseEntity.ok().body(clientResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> updateClient(@PathVariable UUID id,@Validated({Default.class})@RequestBody ClientRequestDTO clientRequestDTO) {
        ClientResponseDTO clientResponseDTO = clientService.updateClient(id, clientRequestDTO);
        return ResponseEntity.ok().body(clientResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable UUID id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
