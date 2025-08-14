package com.mbm.clientservice.mapper;

import com.mbm.clientservice.dto.ClientRequestDTO;
import com.mbm.clientservice.dto.ClientResponseDTO;
import com.mbm.clientservice.modele.Client;

public class ClientMapper {
    // We know that in order to make the servicee communnicating with the controller we should convert the transmitting domain models into a dto
    public static ClientResponseDTO toDto(Client client) {
        ClientResponseDTO dto = new ClientResponseDTO();
        dto.setId(client.getId());
        dto.setNom(client.getNom());
        dto.setEmail(client.getEmail());
        dto.setSiret(client.getSiret());

        return dto;
    }

    // Reversing the first process
    public static Client toModel(ClientRequestDTO clientRequestDTO) {
        Client client = new Client();

        client.setNom(clientRequestDTO.getNom());
        client.setEmail(clientRequestDTO.getEmail());
        client.setSiret(clientRequestDTO.getSiret());

        return client;
    }
}
