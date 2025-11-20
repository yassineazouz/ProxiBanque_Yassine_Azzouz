package com.example.formation.mapper;

import com.example.formation.dto.ClientDto;
import com.example.formation.model.Client;

import java.util.stream.Collectors;

public class ClientMapper {

    public static ClientDto toDto(Client client) {
        ClientDto dto = new ClientDto();
        dto.setId(client.getId());
        dto.setFirstname(client.getFirstname());
        dto.setLastname(client.getLastname());
        dto.setAddress(client.getAddress());
        dto.setPostalCode(client.getPostalCode());
        dto.setCity(client.getCity());
        dto.setPhone(client.getPhone());
        if (client.getAdvisor() != null) {
            dto.setAdvisorId(client.getAdvisor().getId());
        }
        dto.setAccountIds(client.getAccounts().stream().map(a -> a.getId()).collect(Collectors.toList()));
        dto.setCardIds(client.getCards().stream().map(c -> c.getId()).collect(Collectors.toList()));
        return dto;
    }

    public static Client toEntity(ClientDto dto) {
        Client client = new Client();
        client.setFirstname(dto.getFirstname());
        client.setLastname(dto.getLastname());
        client.setAddress(dto.getAddress());
        client.setPostalCode(dto.getPostalCode());
        client.setCity(dto.getCity());
        client.setPhone(dto.getPhone());
        return client;
    }
}
