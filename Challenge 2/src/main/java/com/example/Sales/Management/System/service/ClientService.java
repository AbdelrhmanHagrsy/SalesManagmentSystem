package com.example.Sales.Management.System.service;

import com.example.Sales.Management.System.dto.ClientDto;
import com.example.Sales.Management.System.entity.Client;
import com.example.Sales.Management.System.entity.Product;
import com.example.Sales.Management.System.exception.RecordNotFoundException;
import com.example.Sales.Management.System.mapper.ClientMapper;
import com.example.Sales.Management.System.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ClientService {

    private final ClientRepository clientRepository;
    private  final ClientMapper clientMapper;

    @Autowired
    MessageSource messageSource;

    @Autowired
    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public ClientDto findOne(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if(client.isEmpty()){
            String [] para = {id.toString()};
            throw new RecordNotFoundException(messageSource.getMessage("record.notfound", para, LocaleContextHolder.getLocale()));
        }
        return clientMapper.toDto(client.get());
    }

    private boolean isUpdated(Object data) {
        if (data != null && data.toString().trim().length() > 0) return true;
        return false;
    }

    public Long createClient(ClientDto clientDto) {
        try {
            Client client = clientRepository.save(clientMapper.toEntity(clientDto));
            if (client != null) {
                log.info("Client :"+ client.getName() + " created successfully");
                return client.getId();
            }
        } catch (Exception e) {
            log.error("Error while creating client: {}", e.getMessage());
        }
        throw new RuntimeException("Failed to create client.");
    }

    public Long updateClient(Long id, ClientDto clientDto) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if(clientOptional.isEmpty()) {
            String [] para = {id.toString()};
            throw new RecordNotFoundException(messageSource.getMessage("record.notfound", para, LocaleContextHolder.getLocale()));
        }
        try {
            Client client = clientOptional.get();
            if(isUpdated(clientDto.getAddress()))
                client.setName(clientDto.getName());
            if(isUpdated(clientDto.getLastName()))
                client.setName(clientDto.getName());
            if(isUpdated(clientDto.getEmail()))
                client.setEmail(clientDto.getEmail());
            if(isUpdated(clientDto.getMobile()))
                client.setMobile(clientDto.getMobile());
            clientRepository.save(client);
            log.info("Client with ID :"+ id + " updated successfully");
            return client.getId();
        }catch (Exception e){
            log.error("Error while updating client: {}", e.getMessage());
            throw new RuntimeException("Faild to update client !");
        }
    }

    public boolean deleteClient(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if(clientOptional.isEmpty()) {
            String [] para = {id.toString()};
            throw new RecordNotFoundException(messageSource.getMessage("record.notfound", para, LocaleContextHolder.getLocale()));
        }
        clientRepository.deleteById(id);
        log.info("Client with ID :"+ id + " has deleted successfully");
        return true;
    }
}
