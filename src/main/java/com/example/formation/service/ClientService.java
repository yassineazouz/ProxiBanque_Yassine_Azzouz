package com.example.formation.service;

import com.example.formation.model.*;
import com.example.formation.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final AdvisorRepository advisorRepository;
    private final AccountRepository accountRepository;
    private final CardRepository cardRepository;

    public ClientService(ClientRepository clientRepository,
            AdvisorRepository advisorRepository,
            AccountRepository accountRepository,
            CardRepository cardRepository) {
        this.clientRepository = clientRepository;
        this.advisorRepository = advisorRepository;
        this.accountRepository = accountRepository;
        this.cardRepository = cardRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    @Transactional
    public Client createClient(Client client, Long advisorId) {
        Advisor advisor = advisorRepository.findById(advisorId)
                .orElseThrow(() -> new RuntimeException("Advisor not found"));
        if (advisor.getClients().size() >= Advisor.MAX_CLIENTS) {
            throw new RuntimeException("Advisor already manages 10 clients");
        }
        client.setAdvisor(advisor);
        advisor.getClients().add(client);
        return clientRepository.save(client);
    }

    @Transactional
    public void deleteClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        client.getAccounts().forEach(accountRepository::delete);
        client.getCards().forEach(card -> {
            card.setActive(false);
            cardRepository.save(card);
        });
        clientRepository.delete(client);
    }

    @Transactional
    public Client addAccountToClient(Long clientId, Account account) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        account.setClient(client);
        client.getAccounts().add(account);
        accountRepository.save(account);
        return clientRepository.save(client);
    }

    @Transactional
    public Client addCardToClient(Long clientId, Card card) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        card.setClient(client);
        client.getCards().add(card);
        cardRepository.save(card);
        return clientRepository.save(client);
    }
}
