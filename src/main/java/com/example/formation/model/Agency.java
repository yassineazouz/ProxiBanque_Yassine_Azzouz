package com.example.formation.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Agency {

    @Id
    private String id;

    private LocalDate createdAt = LocalDate.now();
    private String name;

    @OneToOne(mappedBy = "agency", cascade = CascadeType.ALL)
    private Manager manager;

    @OneToMany(mappedBy = "agency", cascade = CascadeType.ALL)
    private List<Advisor> advisors = new ArrayList<>();

    public Agency() {
    }

    public Agency(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public List<Advisor> getAdvisors() {
        return advisors;
    }

    public void addAdvisor(Advisor advisor) {
        advisors.add(advisor);
        advisor.setAgency(this);
    }
}
