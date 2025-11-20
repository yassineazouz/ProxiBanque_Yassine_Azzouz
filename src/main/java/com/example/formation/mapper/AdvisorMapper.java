package com.example.formation.mapper;

import com.example.formation.dto.AdvisorDto;
import com.example.formation.model.Advisor;
import org.springframework.stereotype.Component;

@Component
public class AdvisorMapper {

    public AdvisorDto toDto(Advisor advisor) {
        return new AdvisorDto(
                advisor.getId(),
                advisor.getFirstname(),
                advisor.getLastname(),
                advisor.getAgency().getId());
    }

    public Advisor toEntity(AdvisorDto dto) {
        Advisor advisor = new Advisor();
        advisor.setFirstname(dto.getFirstname());
        advisor.setLastname(dto.getLastname());
        return advisor;
    }
}
