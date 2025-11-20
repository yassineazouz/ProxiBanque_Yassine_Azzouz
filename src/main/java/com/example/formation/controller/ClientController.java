package com.example.formation.controller;

import com.example.formation.model.Account;
import com.example.formation.model.Card;
import com.example.formation.model.Client;
import com.example.formation.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/advisor/{advisorId}")
    public Client createClient(@RequestBody Client client, @PathVariable Long advisorId) {
        return clientService.createClient(client, advisorId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/accounts")
    public Client addAccount(@PathVariable Long id, @RequestBody Account account) {
        return clientService.addAccountToClient(id, account);
    }

    @PostMapping("/{id}/cards")
    public Client addCard(@PathVariable Long id, @RequestBody Card card) {
        return clientService.addCardToClient(id, card);
    }
}
