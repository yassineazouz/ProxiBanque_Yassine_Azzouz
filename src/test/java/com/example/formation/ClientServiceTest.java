package com.example.formation;

import com.example.formation.model.*;
import com.example.formation.repository.*;
import com.example.formation.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ClientServiceTest {

    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private AdvisorRepository advisorRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientService clientService;

    private Agency agency;
    private Advisor advisor;

    @BeforeEach
    public void setup() {
        agency = new Agency();
        agency.setName("Agence Lyon");
        agency = agencyRepository.save(agency);

        advisor = new Advisor();
        advisor.setFirstname("Emma");
        advisor.setLastname("Brown");
        advisor.setAgency(agency);
        advisor = advisorRepository.save(advisor);
    }

    @Test
    public void testCreateClientWithAccounts() {
        Client client = new Client();
        client.setFirstname("Alice");
        client.setLastname("Smith");
        client.setCity("Lyon");
        client.setAdvisor(advisor);

        Client savedClient = clientService.createClient(client, advisor.getId());

        assertNotNull(savedClient.getId());
        assertEquals("Alice", savedClient.getFirstname());
        assertEquals(advisor.getId(), savedClient.getAdvisor().getId());

        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setBalance(new BigDecimal("1000"));
        currentAccount.setClient(savedClient);
        currentAccount = accountRepository.save(currentAccount);

        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setBalance(new BigDecimal("500"));
        savingAccount.setClient(savedClient);
        savingAccount = accountRepository.save(savingAccount);

        assertEquals(savedClient.getId(), currentAccount.getClient().getId());
        assertEquals(savedClient.getId(), savingAccount.getClient().getId());
    }
}
