package com.project.testPan.controller;

import com.project.testPan.entity.Address;
import com.project.testPan.request.AddressRequest;
import com.project.testPan.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService service;

    @PostMapping
    public ResponseEntity<Address> create(@RequestBody AddressRequest address){
        log.info("[CREATE ADDRESS] insert() called - address: {}", address);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(address));
    }

    @GetMapping(value = "{cpf}")
    public ResponseEntity<Address> getId(@PathVariable String cpf){
        log.info("[GET ADDRESS BY ID]  - cpf: {}", cpf);
        return ResponseEntity.ok().body(service.findByCPF(cpf));
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAll(){
        log.info("[GET ADDRESS ]");
        return ResponseEntity.ok().body(service.getAll());
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Address> update(@PathVariable Long id, @RequestBody AddressRequest address){
        log.info("[UPDATE ADDRESS]   called - id: {}", id);
        return ResponseEntity.ok().body(service.update(address));
    }

    @GetMapping(value = "/findCep")
    public ResponseEntity getAddressByCep(@RequestParam(value="cep", required=true, name = "cep") String cep){
        log.info("[FIND CEP]  called- cep: {}", cep);
        return ResponseEntity.ok().body(service.findAddressByCep(cep));
    }

    @GetMapping(value = "/findStates")
    public ResponseEntity getAllStates(){
        log.info("[FIND STATES]  called ");
        return ResponseEntity.ok().body(service.findStates());
    }

    @GetMapping(value = "/findCityByIdUF/{id}")
    public ResponseEntity getCityByIdUF(@PathVariable String id){
        log.info("[find City By Id UF ]  called - UF: {}", id);
        return ResponseEntity.ok().body(service.findCityByIdUF(id));
    }
}
