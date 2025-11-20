package com.example.formation.service;

import com.example.formation.model.Advisor;
import com.example.formation.model.Agency;
import com.example.formation.repository.AdvisorRepository;
import com.example.formation.repository.AgencyRepository;
import org.springframework.stereotype.Service;

@Service
public class AdvisorService {

    private final AdvisorRepository advisorRepository;
    private final AgencyRepository agencyRepository;

    public AdvisorService(AdvisorRepository advisorRepository, AgencyRepository agencyRepository) {
        this.advisorRepository = advisorRepository;
        this.agencyRepository = agencyRepository;
    }

    public Advisor createAdvisor(String firstname, String lastname, String agencyId) {
        Agency agency = agencyRepository.findById(agencyId)
                .orElseThrow(() -> new RuntimeException("Agency not found"));

        Advisor advisor = new Advisor();
        advisor.setFirstname(firstname);
        advisor.setLastname(lastname);
        advisor.setAgency(agency);

        return advisorRepository.save(advisor);
    }
}
