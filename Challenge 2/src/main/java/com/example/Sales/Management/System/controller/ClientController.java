package com.example.Sales.Management.System.controller;

import com.example.Sales.Management.System.dto.ClientDto;
import com.example.Sales.Management.System.dto.ProductDto;
import com.example.Sales.Management.System.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//


@RestController
@RequestMapping("/api/v2")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping( "/client")
    public ResponseEntity<?> createClient(@RequestBody ClientDto clientDto) throws Exception{
        Long id = clientService.createClient(clientDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.findOne(id));
    }

    @GetMapping( "/client/{id}")
    public ResponseEntity<?> createClient(@PathVariable Long id) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findOne(id));
    }

    @PutMapping( "/client/{id}")
    public ResponseEntity<?> updateClient(@PathVariable Long id,@RequestBody ClientDto clientDto) throws Exception{
        Long clientId = clientService.updateClient(id,clientDto);
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findOne(clientId));
    }

    @DeleteMapping( "/client/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) throws Exception{
        clientService.deleteClient(id);
        return ResponseEntity.status(HttpStatus.OK).body("Done");
    }

}
