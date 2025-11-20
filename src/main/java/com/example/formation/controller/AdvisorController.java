package com.example.formation.controller;

import com.example.formation.dto.AdvisorDto;
import com.example.formation.mapper.AdvisorMapper;
import com.example.formation.model.Advisor;
import com.example.formation.service.AdvisorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/advisors")
public class AdvisorController {

    private final AdvisorService advisorService;
    private final AdvisorMapper advisorMapper;

    public AdvisorController(AdvisorService advisorService, AdvisorMapper advisorMapper) {
        this.advisorService = advisorService;
        this.advisorMapper = advisorMapper;
    }

    @PostMapping
    public AdvisorDto createAdvisor(@RequestBody AdvisorDto advisorDto) {
        Advisor advisor = advisorService.createAdvisor(
            advisorDto.getFirstname(),
            advisorDto.getLastname(),
            advisorDto.getAgencyId()
        );
        return advisorMapper.toDto(advisor);
    }
}
