package com.example.formation.controller;

import com.example.formation.dto.ClientDto;
import com.example.formation.mapper.ClientMapper;
import com.example.formation.model.Client;
import com.example.formation.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientDto> getAllClients() {
        return clientService.getAllClients().stream()
                .map(ClientMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id)
                .map(ClientMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/advisor/{advisorId}")
    public ClientDto createClient(@RequestBody ClientDto clientDto, @PathVariable Long advisorId) {
        Client client = ClientMapper.toEntity(clientDto);
        Client savedClient = clientService.createClient(client, advisorId);
        return ClientMapper.toDto(savedClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/accounts")
    public ClientDto addAccount(@PathVariable Long id, @RequestBody com.example.formation.model.Account account) {
        Client client = clientService.addAccountToClient(id, account);
        return ClientMapper.toDto(client);
    }

    @PostMapping("/{id}/cards")
    public ClientDto addCard(@PathVariable Long id, @RequestBody com.example.formation.model.Card card) {
        Client client = clientService.addCardToClient(id, card);
        return ClientMapper.toDto(client);
    }
}
