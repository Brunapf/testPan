package com.project.testPan.controller;

import com.project.testPan.entity.Client;
import com.project.testPan.request.ClientRequest;
import com.project.testPan.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/client")
public class ClientController {

    private final ClientService service;

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody ClientRequest client){
        log.info("[CREATE CLIENT] insert() called - client: {}", client);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(client));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id){
        log.info("[GET CLIENT BY ID]  - id: {}", id);
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAll(){
        log.info("[GET CLIENTS ]");
        return ResponseEntity.ok().body(service.getAll());
    }

    @PutMapping(value = "/{cpf}")
    public ResponseEntity<Client> update(@PathVariable String cpf, @RequestBody ClientRequest client){
        log.info("[UPDATE CLIENT]   called - CPF: {}", cpf);
        return ResponseEntity.ok().body(service.update(client, cpf));
    }

    @GetMapping(value = "/findByCPF/{cpf}")
    public ResponseEntity<Client> getClientByCPF(@RequestParam String cpf){
        log.info("[FIND CLIENT BY CPF]   called - CPF: {}", cpf);
        return ResponseEntity.ok().body(service.findByCPF(cpf));
    }


}
