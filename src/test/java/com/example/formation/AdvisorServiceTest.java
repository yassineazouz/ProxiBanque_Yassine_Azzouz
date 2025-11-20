package com.example.formation;

import com.example.formation.model.Agency;
import com.example.formation.model.Advisor;
import com.example.formation.repository.AgencyRepository;
import com.example.formation.repository.AdvisorRepository;
import com.example.formation.service.AdvisorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class AdvisorServiceTest {

    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private AdvisorRepository advisorRepository;

    @Autowired
    private AdvisorService advisorService;

    private Agency agency;

    @BeforeEach
    public void setup() {
        agency = new Agency();
        agency.setName("Agence Paris");
        agency = agencyRepository.save(agency);
    }

    @Test
    public void testCreateAdvisor() {
        Advisor advisor = advisorService.createAdvisor("John", "Doe", agency.getId());

        assertNotNull(advisor.getId());
        assertEquals("John", advisor.getFirstname());
        assertEquals("Doe", advisor.getLastname());
        assertEquals(agency.getId(), advisor.getAgency().getId());

        Advisor fromDb = advisorRepository.findById(advisor.getId()).orElse(null);
        assertNotNull(fromDb);
        assertEquals("John", fromDb.getFirstname());
    }
}
