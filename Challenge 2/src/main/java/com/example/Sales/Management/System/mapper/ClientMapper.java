package com.example.Sales.Management.System.mapper;

import com.example.Sales.Management.System.dto.ClientDto;
import com.example.Sales.Management.System.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client toEntity(ClientDto clientDto) {
        Client client = Client.builder()
                .address(clientDto.getAddress())
                .email(clientDto.getEmail())
                .mobile(clientDto.getMobile())
                .name(clientDto.getName())
                .lastName(clientDto.getLastName())
                .build();
        if (clientDto.getId() != null)
            client.setId(client.getId());
        return client;
    }

    public  ClientDto toDto(Client client){
        return ClientDto.builder()
                .id(client.getId())
                .address(client.getAddress())
                .name(client.getName())
                .lastName(client.getLastName())
                .mobile(client.getMobile())
                .email(client.getEmail())
                .createdAt(client.getCreatedDate())
                .build();
    }
}
