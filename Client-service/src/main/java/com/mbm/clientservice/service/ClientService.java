package com.mbm.clientservice.service;
import com.mbm.clientservice.dto.*;
import com.mbm.clientservice.mapper.ClientMapper;
import com.mbm.clientservice.modele.Client;
import com.mbm.clientservice.repository.ClientRepository;
import com.mbm.clientservice.exception.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClientService {
    // Dependency injection (Constructor injection)
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    //Handling getting clients from the clients repo
    public List<ClientResponseDTO> getClients(){
        List<Client> clients = clientRepository.findAll();

        // we should convert from data entity into a data transfer object
        return clients.stream().map(ClientMapper::toDto).toList();
    }

    // Handling incoming requests and convert them into data models
    public ClientResponseDTO createClient(ClientRequestDTO clientRequestDTO) {
        if(clientRepository.existsByEmail(clientRequestDTO.getEmail())){
            throw new EmailAlreadyUsedException("A client with this email : "+clientRequestDTO.getEmail()+" already in used.");
        }else if(clientRepository.existsBySiret(clientRequestDTO.getSiret())){
            throw new SiretAlreadyUsedException("A client with this siret : "+clientRequestDTO.getSiret()+" already in used.");
        }

        Client newClient = clientRepository.save(ClientMapper.toModel(clientRequestDTO));

        return ClientMapper.toDto(newClient);
    }

    public ClientResponseDTO updateClient(UUID clientId, ClientRequestDTO clientRequestDTO) {
        Client client = clientRepository.findById(clientId).orElseThrow(()->
                new ClientNotFoundException("Patient with this Id : "+clientId+" not found !"));
        if(clientRepository.existsByEmail(clientRequestDTO.getEmail())){
            throw new EmailAlreadyUsedException("A client with this email : "+clientRequestDTO.getEmail()+" already in used.");
        }else if(clientRepository.existsBySiret(clientRequestDTO.getSiret())){
            throw new SiretAlreadyUsedException("A client with this siret : "+clientRequestDTO.getSiret()+" already in used.");
        }

        client.setNom(clientRequestDTO.getNom());
        client.setEmail(clientRequestDTO.getEmail());
        client.setSiret(clientRequestDTO.getSiret());

        Client updatedClient = clientRepository.save(client);
        return ClientMapper.toDto(updatedClient);
    }

    public void deleteClient(UUID clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(()->
                new ClientNotFoundException("Patient with this Id : "+clientId+" not found !"));
        clientRepository.delete(client);
    }
}
