package com.example.formation.dto;

public class AdvisorDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String agencyId;


    public AdvisorDto() {
    }

    public AdvisorDto(Long id, String firstname, String lastname, String agencyId) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.agencyId = agencyId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }
}
